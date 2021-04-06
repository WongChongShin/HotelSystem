package com.example.mobilehotelmanagementsystem

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CustomerMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_main)

        val customerUsername = intent?.getStringExtra("username")
        val customerSignInUsername = findViewById<TextView>(R.id.customer_username_textview)

        customerSignInUsername.setText(customerUsername)


        val bookingButton = findViewById<Button>(R.id.bookingBtn)
        bookingButton.setOnClickListener {

            /*val database = FirebaseDatabase.getInstance();
            val roomDatabaseRef = database.getReference("Room booking");

            val roomStatus = StringBuilder()
            val roomStatusArr = ArrayList<String>()
            val getRoomData = object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {

                }

                override fun onDataChange(snapshot: DataSnapshot) {

                    for (s in snapshot.children) {
                        val custRoomId = s.child("RoomNo").getValue().toString()
                        roomStatus.append("${custRoomId}\n")
                        roomStatusArr.add(custRoomId)

                    }


                }
            }

            val q: Query = roomDatabaseRef.orderByChild("Status").equalTo("full")

            q.addValueEventListener(getRoomData)
            q.addListenerForSingleValueEvent(getRoomData)*/

            val bookingRoom = Intent(this, Customer_select_room::class.java)
            //for (i in 0 until 8) {
                //intent.putExtra("RoomStatusArr0", roomStatusArr.toString())
                startActivity(bookingRoom)
            //}
        }

        //-------------Sui Bing(service request button press)----------------
        //val serviceRequestBtn = findViewById<Button>(R.id. )
        //serviceRequestBtn.setOnClickListener{

        //val serviceRequest= Intent(this,"fill in your class name")
        //startActivity(serviceRequest)
    //}
    }
}