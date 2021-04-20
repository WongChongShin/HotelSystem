package com.example.mobilehotelmanagementsystem

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class Customer_select_room : AppCompatActivity(),Customer_room_adapter.OnItemClickListener {

    private val roomNoArr = ArrayList<String>()
    private val roomDescArr = ArrayList<String>()
    private val roomPriceArr = ArrayList<String>()
    private val roomStatusArr = ArrayList<String>()
    private val database = FirebaseDatabase.getInstance()
    private val roomDatabaseRef = database.getReference("Room booking");
    private lateinit var roomList: ArrayList<customer_room_list>
    private lateinit var recycleView: RecyclerView
    private var room_search_input:EditText?=null
    private var customerUsername:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_select_room)

        customerUsername = intent?.getStringExtra("username")

        recycleView=findViewById(R.id.customer_roomList)
        recycleView?.setHasFixedSize(true)
        recycleView?.layoutManager = LinearLayoutManager(this)

        roomList= arrayListOf<customer_room_list>()



        retriveAllData()

        room_search_input= findViewById<EditText>(R.id.customer_room_search_input)
        room_search_input!!.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(word: CharSequence, start: Int,
                                       before: Int, count: Int) {
                getSearchData(word.toString())
            }
        })



        //------------------------checking date
        val getRoomCheckOutData = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {

                for (s in snapshot.children) {
                    val custRoomId = s.child("RoomNo").getValue().toString()
                    val roomCheckOutDate = s.child("CheckOut").getValue().toString()
                    val sdf = SimpleDateFormat("dd/MM/yyyy")
                    val strDate: Date = sdf.parse(roomCheckOutDate)
                    if (System.currentTimeMillis() > strDate.time) {

                        roomDatabaseRef.child(custRoomId).child("Status").setValue("empty")

                    } else {
                        roomDatabaseRef.child(custRoomId).child("Status").setValue("full")


                    }
                }

            }
        }


        val checkOutQuery: Query = roomDatabaseRef.orderByChild("CheckOut")

        checkOutQuery.addValueEventListener(getRoomCheckOutData)
        checkOutQuery.addListenerForSingleValueEvent(getRoomCheckOutData)






        val roomBackBtn: ImageView = findViewById<ImageView>(R.id.select_room_back);

        roomBackBtn.setOnClickListener {
            val backCustomerMain = Intent(this, CustomerMainActivity::class.java)

            startActivity(backCustomerMain)
        }

    }
    private fun retriveAllData(){
        roomDatabaseRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(snapshot: DataSnapshot) {


                roomList.clear()
                if (room_search_input!!.text.toString() == "") {
                        for (s in snapshot.children) {
                            val custRoomId = s.child("RoomNo").getValue().toString()
                            val custRoomDesc = s.child("Description").getValue().toString()
                            val custRoomPrice = s.child("OriginalPrice").getValue().toString()
                            val custRoomStatus = s.child("Status").getValue().toString()
                            val room = s.getValue(customer_room_list::class.java)
                            room!!.roomNo = custRoomId
                            roomNoArr.add(custRoomId)
                            room!!.roomDesc = custRoomDesc
                            roomDescArr.add(custRoomDesc)
                            room!!.roomPrice = custRoomPrice
                            roomPriceArr.add(custRoomPrice)
                            room!!.roomStatus = custRoomStatus
                            roomStatusArr.add(custRoomPrice)
                            roomList.add(room)

                        }

                        val adapter = Customer_room_adapter(this@Customer_select_room, roomList, this@Customer_select_room)

                        recycleView.setAdapter(adapter)

                }
            }
        })
    }

    private fun getSearchData(search_input:String) {

        val roomSearchbtn: ImageView = findViewById<ImageView>(R.id.customer_room_search);

        //val searchRoomNo: Query = roomDatabaseRef.equalTo("100")
        val searchRoom:Query=FirebaseDatabase.getInstance().reference.child("Room booking").orderByChild("RoomNo").startAt(search_input).endAt(search_input+"\ufbff")
        val searchResult = ArrayList<String>()

        searchRoom.addValueEventListener(object:ValueEventListener{
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {

                roomList.clear()
                for (s in snapshot.children) {
                    val custRoomId = s.child("RoomNo").getValue().toString()
                    val custRoomDesc = s.child("Description").getValue().toString()
                    val custRoomPrice = s.child("OriginalPrice").getValue().toString()
                    val custRoomStatus = s.child("Status").getValue().toString()
                    val room = s.getValue(customer_room_list::class.java)
                    room!!.roomNo = custRoomId
                    roomNoArr.add(custRoomId)
                    room!!.roomDesc = custRoomDesc
                    roomDescArr.add(custRoomDesc)
                    room!!.roomPrice = custRoomPrice
                    roomPriceArr.add(custRoomPrice)
                    room!!.roomStatus = custRoomStatus
                    roomStatusArr.add(custRoomPrice)
                    roomList.add(room)

                }
                val adapter = Customer_room_adapter(this@Customer_select_room, roomList, this@Customer_select_room)

                recycleView.setAdapter(adapter)

            }


        })

        //searchRoom.addListenerForSingleValueEvent(getRoomNoData)
    }




    override fun onItemClick(position: Int) {
        val customerRoomIntent = Intent(this, Customer_select_detail::class.java)
        customerRoomIntent.putExtra("room_no", roomNoArr[position].toString());
        customerRoomIntent.putExtra("room_desc", roomDescArr[position].toString());
        customerRoomIntent.putExtra("room_price", roomPriceArr[position].toString());
        customerRoomIntent.putExtra("username", customerUsername.toString())

        startActivity(customerRoomIntent)
    }
}