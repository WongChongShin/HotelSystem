package com.example.mobilehotelmanagementsystem

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class cleaning_list : Fragment(),CleanServiceAdapter.OnItemClickListener {

    //Recycler View
    private val cleanNameArr = ArrayList<String>()
    private val cleanPhoneArr = ArrayList<String>()
    private val db = FirebaseDatabase.getInstance()
    private val databaseReference = db.getReference("Cleaning List")
    private lateinit var cleanServiceList: ArrayList<CleanServiceModel>
    private lateinit var recyclerView: RecyclerView
    var cleanServiceAdapter: CleanServiceAdapter? = null
    var clean_search_input: EditText? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_cleaning_list,container,false)

        val btnAddClean = v.findViewById<ImageView>(R.id.add_clean_fab)

        //Recycler View stuff
        recyclerView = v.findViewById(R.id.clean_rv)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(this.context)
        cleanServiceList= arrayListOf<CleanServiceModel>()

        //add new cleaning service intent
        btnAddClean.setOnClickListener(){

            val addNewCleanIntent = Intent(getActivity(), add_new_cleaning_service::class.java)
            startActivity(addNewCleanIntent)
        }

        //search data
        clean_search_input= v.findViewById<EditText>(R.id.editSearch2)
        clean_search_input!!.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(word: CharSequence, start: Int,
                                       before: Int, count: Int) {

            }

        })

        //get data
        var d = databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(snapshot: DataSnapshot) {

                cleanServiceList.clear()
                if (clean_search_input!!.text.toString() == "") {
                    for (s in snapshot.children) {

                        val cleanName = s.child("Name").getValue().toString()
                        val cleanPhone = s.child("Phone").getValue().toString()
                        val cs = s.getValue(CleanServiceModel::class.java)

                        cs!!.userName = cleanName
                        cleanNameArr.add(cleanName)
                        cs!!.userPhone = cleanPhone
                        cleanPhoneArr.add(cleanPhone)
                        cleanServiceList.add(cs)

                    }
                    val adapter = CleanServiceAdapter(this@cleaning_list, cleanServiceList, this@cleaning_list)

                    recyclerView.setAdapter(adapter)
                }
            }
        })

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
       myRef.addListenerForSingleValueEvent(getCleanData)


        val query: Query = databaseReference//.child("Name")
        val firebaseRecyclerOptions: FirebaseRecyclerOptions<CleanServiceModel> = FirebaseRecyclerOptions.Builder<CleanServiceModel>()
                .setQuery(query, CleanServiceModel::class.java)
                .build()

        cleanServiceAdapter = CleanServiceAdapter(firebaseRecyclerOptions)

        recyclerView.layoutManager =LinearLayoutManager(this.context)

        recyclerView.adapter = cleanServiceAdapter*/

        return v
    }

    override fun onItemClick(position: Int) {
        val cleanIntent = Intent(getActivity(), add_new_cleaning_service::class.java)
        cleanIntent.putExtra("Name", cleanNameArr[position].toString());
        cleanIntent.putExtra("Phone", cleanPhoneArr[position].toString());

        startActivity(cleanIntent)
    }

    /*override fun onStart() {
        super.onStart()
        cleanServiceAdapter!!.startListening()
    }

    override fun onDestroy() {
        super.onDestroy()
        cleanServiceAdapter!!.stopListening()
    }*/
}