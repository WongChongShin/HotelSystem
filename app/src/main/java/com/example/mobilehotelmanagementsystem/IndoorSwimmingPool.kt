package com.example.mobilehotelmanagementsystem

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class IndoorSwimmingPool : AppCompatActivity() ,PoolAdapter.OnItemClickListener {

    private val poolRoomNoArr = ArrayList<String>()
    private val poolPhoneArr = ArrayList<String>()
    private val poolTimeArr = ArrayList<String>()
    private val bd: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference: DatabaseReference = bd.getReference("Pool List")
    private lateinit var poolList: ArrayList<PoolModel>
    private lateinit var recyclerView: RecyclerView
    var pool_search_input: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_indoor_swimming_pool)

        //Recycler View stuff
        recyclerView = findViewById(R.id.pool_list_view)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        poolList = arrayListOf<PoolModel>()

        val deletePoolListBtn = findViewById<ImageView>(R.id.delete_row_pool)

        val btnAddPool = findViewById<ImageView>(R.id.add_pool_logo)

        btnAddPool.setOnClickListener() {

            val addNewPoolIntent = Intent(this, AddNewPoolReservation::class.java)
            startActivity(addNewPoolIntent)
        }

        val poolBackBtn: ImageView = findViewById<ImageView>(R.id.pool_page_back_logo);

        poolBackBtn.setOnClickListener {
            val backPoolMain = Intent(this, facilities_monitoring::class.java)

            startActivity(backPoolMain)
        }


        //search data
        pool_search_input = findViewById<EditText>(R.id.pool_editSearch)
        pool_search_input!!.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(word: CharSequence, start: Int,
                                       before: Int, count: Int) {

                val pool_search_input: String = word.toString()

                val poolSearchRoom: Query = FirebaseDatabase.getInstance().reference.child("Pool List")
                        .orderByChild("Room No").startAt(pool_search_input).endAt(pool_search_input + "\ufbff")

                poolSearchRoom.addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(error: DatabaseError) {

                    }

                    override fun onDataChange(snapshot: DataSnapshot) {

                        poolList.clear()
                        for (s in snapshot.children) {

                            val room_no = s.child("Room No").getValue().toString()
                            val phone = s.child("Phone").getValue().toString()
                            val time = s.child("Time").getValue().toString()
                            val pool = s.getValue(PoolModel::class.java)

                            pool!!.room_no = room_no
                            poolRoomNoArr.add(room_no)
                            pool!!.phone = phone
                            poolPhoneArr.add(phone)
                            pool!!.time= time
                            poolTimeArr.add(time)

                        }
                        val adapter = PoolAdapter(this@IndoorSwimmingPool, poolList,this@IndoorSwimmingPool)

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

                poolList.clear()
                if (pool_search_input!!.text.toString() == "") {
                    for (s in snapshot.children) {

                        val room_no = s.child("Room No").getValue().toString()
                        val phone = s.child("Phone").getValue().toString()
                        val time = s.child("Time").getValue().toString()
                        val pool = s.getValue(PoolModel::class.java)

                        pool!!.room_no = room_no
                        poolRoomNoArr.add(room_no)
                        pool!!.phone = phone
                        poolPhoneArr.add(phone)
                        pool!!.time = time
                        poolTimeArr.add(time)
                        poolList.add(pool)

                    }
                    val adapter = PoolAdapter(this@IndoorSwimmingPool, poolList, this@IndoorSwimmingPool)

                    recyclerView.setAdapter(adapter)
                }
            }
        })
    }

    override fun onItemClick(position: Int) {
        val deletePoolListBtn = findViewById<ImageView>(R.id.delete_row_pool)
        deletePoolListBtn.setOnClickListener {

            databaseReference.child("Name").removeValue()

        }

        val editPoolListBtn = findViewById<ImageView>(R.id.edit_row_pool)
        editPoolListBtn.setOnClickListener {

            val poolIntent = Intent(this, EditPoolList::class.java)

            poolIntent.putExtra("Room No", poolRoomNoArr[position].toString());
            poolIntent.putExtra("Phone", poolPhoneArr[position].toString());
            poolIntent.putExtra("Time", poolTimeArr[position].toString());

            startActivity(poolIntent)


        }
    }
}