package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class Customer_purchase : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_purchase)

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

        //customerDesc.setText(roomDescOutput)
        
        //--------------------price--------------
        val customerPrice = intent?.getStringExtra("room_price")
        val roomPriceOutput = findViewById<TextView>(R.id.room_price_output)

        roomPriceOutput.setText(customerPrice)

        //--------------------check in date--------------
        val customerCheckIn = intent?.getStringExtra("checkIn_date")
        val roomCheckInOutput = findViewById<TextView>(R.id.room_check_in_output)

        //customerCheckIn.setText(roomCheckInOutput)

        //--------------------check out date--------------
        val customerCheckOut = intent?.getStringExtra("checkOut_date")
        val roomCheckOutOutput = findViewById<TextView>(R.id.room_check_out_output)

        //customerCheckOut.setText(roomCheckOutOutput)


        val purchaseButton = findViewById<Button>(R.id.select_detail_purchase)
        purchaseButton.setOnClickListener{

            val pruchaseRoom = Intent(this,Customer_pay_successful::class.java)
            startActivity(pruchaseRoom)
        }




    }
}

