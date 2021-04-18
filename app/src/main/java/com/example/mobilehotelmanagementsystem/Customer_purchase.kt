package com.example.mobilehotelmanagementsystem

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class Customer_purchase : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_purchase)
        val customerUsername = intent?.getStringExtra("username")

        val purchaseBackBtn: ImageView = findViewById<ImageView>(R.id.select_purchase_back);

        purchaseBackBtn.setOnClickListener{
            val backCustomerDetail = Intent(this, Customer_select_detail::class.java)
            startActivity(backCustomerDetail)
        }

        //--------------------room no--------------
        val customerRoomNo = intent?.getStringExtra("room_no")
        val roomNoOutput = findViewById<TextView>(R.id.room_no_output)

        roomNoOutput.setText(customerRoomNo)

        //--------------------description--------------
        val customerDesc = intent?.getStringExtra("room_desc")
        val roomDescOutput= findViewById<TextView>(R.id.room_desc_output)

        roomDescOutput.setText(customerDesc)


        //--------------------price--------------
        val customerPrice = intent?.getStringExtra("room_price")
        val roomPriceOutput = findViewById<TextView>(R.id.room_price_output)

        roomPriceOutput.setText(customerPrice)

        //--------------------check in date--------------
        val customerCheckIn = intent?.getStringExtra("checkIn_date")
        val roomCheckInOutput = findViewById<TextView>(R.id.room_check_in_output)

        roomCheckInOutput.setText(customerCheckIn)

        //--------------------check out date--------------
        val customerCheckOut = intent?.getStringExtra("checkOut_date")
        val roomCheckOutOutput = findViewById<TextView>(R.id.room_check_out_output)

        roomCheckOutOutput.setText(customerCheckOut)


        val purchaseButton = findViewById<Button>(R.id.select_detail_purchase)
        purchaseButton.setOnClickListener{

            val pruchaseRoom = Intent(this,Customer_pay_successful::class.java)

            val database = FirebaseDatabase.getInstance();
            val myRef = database.getReference("Room booking");
            val custRef = database.getReference("Customer Account");

            val roomID:String= findViewById<TextView>(R.id.room_no_output).text.toString()
            val roomDesc:String=findViewById<TextView>(R.id.room_desc_output).text.toString()
            val roomCheckIn:String=findViewById<TextView>(R.id.room_check_in_output).text.toString()
            val roomCheckOut:String=findViewById<TextView>(R.id.room_check_out_output).text.toString()
            val roomPurchase:String=findViewById<TextView>(R.id.room_price_output).text.toString()
            val roomActivate:String= "full"

            val getCustomerAccountData = object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {

                }

                override fun onDataChange(snapshot: DataSnapshot) {

                    for (s in snapshot.children) {
                        val custName=s.child("CustomerName").getValue().toString()

                        if (custName==customerUsername) {
                            custRef.child(custName).child("RoomNo").setValue(roomID)

                        }
                    }

                }


            }
            val userNameQuery: Query = custRef.orderByChild("CustomerName")

            userNameQuery.addValueEventListener(getCustomerAccountData)
            userNameQuery.addListenerForSingleValueEvent(getCustomerAccountData)


            myRef.child(roomID).child("RoomNo").setValue(roomID)
            myRef.child(roomID).child("Description").setValue(roomDesc)
            myRef.child(roomID).child("CheckIn").setValue(roomCheckIn)
            myRef.child(roomID).child("CheckOut").setValue(roomCheckOut)
            myRef.child(roomID).child("Purchase").setValue(roomPurchase)
            myRef.child(roomID).child("Status").setValue(roomActivate)
            startActivity(pruchaseRoom)
        }




    }
}

