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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class cleaning_list : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_cleaning_list,container,false)

        val btnAddClean = v.findViewById<ImageView>(R.id.add_clean_fab)

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Cleaning List")

        btnAddClean.setOnClickListener(){

            val addNewCleanIntent = Intent(getActivity(), add_new_cleaning_service::class.java)
            startActivity(addNewCleanIntent)
        }

        var getCleanData = object:ValueEventListener{
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                var sbRoom = StringBuilder()

                for(s in snapshot.children){
                    val name = s.child("Name").getValue()
                    sbRoom.append("${name}\n")
                }
                v.findViewById<TextView>(R.id.clean_name_result)
            }


        }
        myRef.addValueEventListener(getCleanData)
        myRef.addListenerForSingleValueEvent(getCleanData)


        return v
    }
}