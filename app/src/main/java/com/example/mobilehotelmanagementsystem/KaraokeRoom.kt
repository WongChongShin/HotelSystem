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

class KaraokeRoom : AppCompatActivity(),KaraokeAdapter.OnItemClickListener {

    private val karaokeRoomNoArr = ArrayList<String>()
    private val karaokePhoneArr = ArrayList<String>()
    private val karaokeTimeArr = ArrayList<String>()
    private val bd: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference: DatabaseReference = bd.getReference("Karaoke List")
    private lateinit var karaokeList: ArrayList<KaraokeModel>
    private lateinit var recyclerView: RecyclerView
    var karaoke_search_input: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_karaoke_room)

        //Recycler View stuff
        recyclerView = findViewById(R.id.karaoke_list_view)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        karaokeList = arrayListOf<KaraokeModel>()

        val deleteKaraokeListBtn = findViewById<ImageView>(R.id.delete_row_karaoke)

        val btnAddKaraoke = findViewById<ImageView>(R.id.add_karaoke_logo)

        btnAddKaraoke.setOnClickListener() {

            val addNewKaraokeIntent = Intent(this, AddNewKaraokeReservation::class.java)
            startActivity(addNewKaraokeIntent)
        }

        val karaokeBackBtn: ImageView = findViewById<ImageView>(R.id.karaoke_page_back_logo);

        karaokeBackBtn.setOnClickListener {
            val backKaraokeMain = Intent(this, facilities_monitoring::class.java)

            startActivity(backKaraokeMain)
        }


        //search data
        karaoke_search_input = findViewById<EditText>(R.id.karaoke_editSearch)
        karaoke_search_input!!.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(word: CharSequence, start: Int,
                                       before: Int, count: Int) {

                val karaoke_search_input: String = word.toString()

                val karaokeSearchRoom: Query = FirebaseDatabase.getInstance().reference.child("Karaoke List")
                        .orderByChild("Room No").startAt(karaoke_search_input).endAt(karaoke_search_input + "\ufbff")

                karaokeSearchRoom.addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(error: DatabaseError) {

                    }

                    override fun onDataChange(snapshot: DataSnapshot) {

                        karaokeList.clear()
                        for (s in snapshot.children) {

                            val room_no = s.child("Room No").getValue().toString()
                            val phone = s.child("Phone").getValue().toString()
                            val time = s.child("Time").getValue().toString()
                            val karaoke = s.getValue(KaraokeModel::class.java)

                            karaoke!!.room_no = room_no
                            karaokeRoomNoArr.add(room_no)
                            karaoke!!.phone = phone
                            karaokePhoneArr.add(phone)
                            karaoke!!.time= time
                            karaokeTimeArr.add(time)

                        }
                        val adapter = KaraokeAdapter(this@KaraokeRoom, karaokeList,this@KaraokeRoom)

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

                karaokeList.clear()
                if (karaoke_search_input!!.text.toString() == "") {
                    for (s in snapshot.children) {

                        val room_no = s.child("Room No").getValue().toString()
                        val phone = s.child("Phone").getValue().toString()
                        val time = s.child("Time").getValue().toString()
                        val karaoke = s.getValue(KaraokeModel::class.java)

                        karaoke!!.room_no = room_no
                        karaokeRoomNoArr.add(room_no)
                        karaoke!!.phone = phone
                        karaokePhoneArr.add(phone)
                        karaoke!!.time = time
                        karaokeTimeArr.add(time)
                        karaokeList.add(karaoke)

                    }
                    val adapter = KaraokeAdapter(this@KaraokeRoom, karaokeList, this@KaraokeRoom)

                    recyclerView.setAdapter(adapter)
                }
            }
        })
    }

    override fun onItemClick(position: Int) {
        val deleteKaraokeListBtn = findViewById<ImageView>(R.id.delete_row_karaoke)
        deleteKaraokeListBtn.setOnClickListener {

            karaokeRoomNoArr[position] = databaseReference.child("Room No").removeValue().toString()
            karaokePhoneArr[position] = databaseReference.child("Phone").removeValue().toString()
            karaokeTimeArr[position] = databaseReference.child("Time").removeValue().toString()

        }

            val karaokeIntent = Intent(this, EditKaraokeList::class.java)

            karaokeIntent.putExtra("Room No", karaokeRoomNoArr[position].toString());
            karaokeIntent.putExtra("Phone", karaokePhoneArr[position].toString());
            karaokeIntent.putExtra("Time", karaokeTimeArr[position].toString());

            startActivity(karaokeIntent)

    }
}