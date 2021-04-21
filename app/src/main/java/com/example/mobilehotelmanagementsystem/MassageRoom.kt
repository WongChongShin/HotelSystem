package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class MassageRoom : AppCompatActivity(), MassageAdapter.OnItemClickListener {

    private val massageRoomNoArr = ArrayList<String>()
    private val massagePhoneArr = ArrayList<String>()
    private val massageTimeArr = ArrayList<String>()
    private val bd: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference: DatabaseReference = bd.getReference("Massage List")
    private lateinit var massageList: ArrayList<MassageModel>
    private lateinit var recyclerView: RecyclerView
    var massage_search_input: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_massage_room)

        //Recycler View stuff
        recyclerView = findViewById(R.id.massage_list_view)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        massageList = arrayListOf<MassageModel>()

        val deleteMassageListBtn = findViewById<ImageView>(R.id.delete_row_massage)

        val btnAddMassage = findViewById<ImageView>(R.id.add_massage_logo)

        btnAddMassage.setOnClickListener() {

            val addNewMassageIntent = Intent(this, AddNewMassageReservation::class.java)
            startActivity(addNewMassageIntent)
        }

        val massageBackBtn: ImageView = findViewById<ImageView>(R.id.massage_page_back_logo);

        massageBackBtn.setOnClickListener {
            val backMassageMain = Intent(this, facilities_monitoring::class.java)

            startActivity(backMassageMain)
        }


        //search data
        massage_search_input = findViewById<EditText>(R.id.massage_editSearch)
        massage_search_input!!.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(word: CharSequence, start: Int,
                                       before: Int, count: Int) {

                val massage_search_input: String = word.toString()

                val massageSearchRoom: Query = FirebaseDatabase.getInstance().reference.child("Massage List")
                        .orderByChild("Room No").startAt(massage_search_input).endAt(massage_search_input + "\ufbff")

                massageSearchRoom.addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(error: DatabaseError) {

                    }

                    override fun onDataChange(snapshot: DataSnapshot) {

                        massageList.clear()
                        for (s in snapshot.children) {

                            val room_no = s.child("Room No").getValue().toString()
                            val phone = s.child("Phone").getValue().toString()
                            val time = s.child("Time").getValue().toString()
                            val massage = s.getValue(MassageModel::class.java)

                            massage!!.room_no = room_no
                            massageRoomNoArr.add(room_no)
                            massage!!.phone = phone
                            massagePhoneArr.add(phone)
                            massage!!.time= time
                            massageTimeArr.add(time)

                        }
                        val adapter = MassageAdapter(this@MassageRoom, massageList,this@MassageRoom)

                        recyclerView.setAdapter(adapter)
                    }

                })
            }
        })

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(snapshot: DataSnapshot) {

                massageList.clear()
                if (massage_search_input!!.text.toString() == "") {
                    for (s in snapshot.children) {

                        val room_no = s.child("Room No").getValue().toString()
                        val phone = s.child("Phone").getValue().toString()
                        val time = s.child("Time").getValue().toString()
                        val massage = s.getValue(MassageModel::class.java)

                        massage!!.room_no = room_no
                        massageRoomNoArr.add(room_no)
                        massage!!.phone = phone
                        massagePhoneArr.add(phone)
                        massage!!.time = time
                        massageTimeArr.add(time)
                        massageList.add(massage)

                    }
                    val adapter = MassageAdapter(this@MassageRoom, massageList, this@MassageRoom)

                    recyclerView.setAdapter(adapter)
                }
            }
        })
    }

    override fun onItemClick(position: Int) {
        val deleteMassageListBtn = findViewById<ImageView>(R.id.delete_row_massage)
        deleteMassageListBtn.setOnClickListener {

            massageRoomNoArr[position] = databaseReference.child("Room No").removeValue().toString()
            massagePhoneArr[position] = databaseReference.child("Phone").removeValue().toString()
            massageTimeArr[position] = databaseReference.child("Time").removeValue().toString()

        }

            val massageIntent = Intent(this, EditMassageList::class.java)

            massageIntent.putExtra("Room No", massageRoomNoArr[position].toString());
            massageIntent.putExtra("Phone", massagePhoneArr[position].toString());
            massageIntent.putExtra("Time", massageTimeArr[position].toString());

            startActivity(massageIntent)

    }
}