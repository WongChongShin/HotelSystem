package com.example.mobilehotelmanagementsystem

import android.content.Intent
import android.database.DatabaseUtils
import android.os.Bundle
import android.service.controls.actions.FloatAction
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.*

class cleaning_list : Fragment() {

    //Recycler View
    private val db:FirebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference: DatabaseReference = db.getReference("Cleaning List")
    private var cleanServiceAdapter: CleanServiceAdapter? = null;


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //val database = FirebaseDatabase.getInstance()
        //val myRef = database.getReference("Cleaning List")

        val v = inflater.inflate(R.layout.fragment_cleaning_list,container,false)

        val btnAddClean = v.findViewById<ImageView>(R.id.add_clean_fab)

        btnAddClean.setOnClickListener(){

            val addNewCleanIntent = Intent(getActivity(), add_new_cleaning_service::class.java)
            startActivity(addNewCleanIntent)
        }

        /*var getCleanData = object:ValueEventListener{
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                var sbRoom = StringBuilder()

                for(s in snapshot.children){
                    val name = s.child("Name").getValue()
                    sbRoom.append("${name}\n")
                }
                v.findViewById<TextView>(R.id.clean_name_result)//.setText(sbRoom)
            }


        }
        myRef.addValueEventListener(getCleanData)
        myRef.addListenerForSingleValueEvent(getCleanData)*/

        //Recycler View

            val recyclerView: RecyclerView = v.findViewById(R.id.clean_rv)
            val query: Query = databaseReference
            val firebaseRecyclerOptions: FirebaseRecyclerOptions<CleanServiceModel> = FirebaseRecyclerOptions.Builder<CleanServiceModel>()
                    .setQuery(query, CleanServiceModel::class.java)
                    .build()

            cleanServiceAdapter = CleanServiceAdapter(firebaseRecyclerOptions)

            recyclerView.layoutManager = LinearLayoutManager(this.context)

            recyclerView.adapter = cleanServiceAdapter

        return v
    }

    override fun onStart() {
        super.onStart()
        cleanServiceAdapter!!.startListening()
    }

    override fun onDestroy() {
        super.onDestroy()
        cleanServiceAdapter!!.stopListening()
    }
}