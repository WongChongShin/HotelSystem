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

    //Retrieve data to recycler view declaration
    private val cleanRoomArr = ArrayList<String>()
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


        //get data
        var d = databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(snapshot: DataSnapshot) {

                cleanServiceList.clear()
                if (clean_search_input!!.text.toString() == "") {
                    for (s in snapshot.children) {

                        val cleanRoom = s.child("Room").getValue().toString()
                        val cleanName = s.child("Name").getValue().toString()
                        val cleanPhone = s.child("Phone").getValue().toString()
                        val cs = s.getValue(CleanServiceModel::class.java)

                        cs!!.userRoom = cleanRoom
                        cleanRoomArr.add(cleanRoom)
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

        //search data
        clean_search_input= v.findViewById<EditText>(R.id.clean_editsearch)
        clean_search_input!!.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(word: CharSequence, start: Int, before: Int, count: Int) {

                //val clean_search_input:String = word.toString()

                val cleanSearchRoom:Query=FirebaseDatabase.getInstance().reference.child("Cleaning List")
                        .orderByChild("Room").startAt(word.toString()).endAt(word.toString()+"\ufbff")

                cleanSearchRoom.addValueEventListener(object:ValueEventListener{
                    override fun onCancelled(error: DatabaseError) {

                    }

                    override fun onDataChange(snapshot: DataSnapshot) {

                        cleanServiceList.clear()
                        for (s in snapshot.children) {
                            val cleanRoom = s.child("Room").getValue().toString()
                            val cleanName = s.child("Name").getValue().toString()
                            val cleanPhone = s.child("Phone").getValue().toString()
                            val cs = s.getValue(CleanServiceModel::class.java)
                            cs!!.userRoom = cleanRoom
                            cleanRoomArr.add(cleanRoom)
                            cs!!.userName = cleanName
                            cleanNameArr.add(cleanName)
                            cs!!.userPhone = cleanPhone
                            cleanPhoneArr.add(cleanPhone)
                            cleanServiceList.add(cs)

                        }
                        val adapter = CleanServiceAdapter(this@cleaning_list, cleanServiceList, this@cleaning_list)

                        recyclerView.setAdapter(adapter)

                    }
                })
            }

        })

        return v
    }

    override fun onItemClick(position: Int) {
        val cleanIntent = Intent(getActivity(), edit_cleaning_service::class.java);
        cleanIntent.putExtra("Room", cleanRoomArr[position].toString());
        cleanIntent.putExtra("Name", cleanNameArr[position].toString());
        cleanIntent.putExtra("Phone", cleanPhoneArr[position].toString());

        startActivity(cleanIntent)
    }
}