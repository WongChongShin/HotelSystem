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
        val CustomerEmail = intent?.getStringExtra("email")
        val customerSignInUsername = findViewById<TextView>(R.id.customer_username_textview)

        customerSignInUsername.setText(customerUsername)


        val bookingButton = findViewById<Button>(R.id.bookingBtn)
        bookingButton.setOnClickListener {

            val bookingRoom = Intent(this, Customer_select_room::class.java)

            bookingRoom.putExtra("username", customerUsername.toString())
            startActivity(bookingRoom)
        }

        //-------------Sui Bin(service request button press)----------------
        val serviceRequestBtn = findViewById<Button>(R.id.serviceBtn )
        serviceRequestBtn.setOnClickListener{

            val serviceRequest= Intent(this, Customer_select_service::class.java)
            serviceRequest.putExtra("username", customerUsername)
            serviceRequest.putExtra("email", CustomerEmail)
            startActivity(serviceRequest)
        }
    }
}