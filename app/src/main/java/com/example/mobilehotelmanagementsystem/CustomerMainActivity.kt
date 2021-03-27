package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class CustomerMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_main)

        val customerUsername = intent?.getStringExtra("username")
        val customerSignInUsername = findViewById<TextView>(R.id.customer_username_textview)

        customerSignInUsername.setText(customerUsername)


        val bookingButton = findViewById<Button>(R.id.bookingBtn)
        bookingButton.setOnClickListener{

            val bookingRoom = Intent(this,Customer_select_room::class.java)
            startActivity(bookingRoom)
        }

        //-------------Sui Bing(service request button press)----------------
        //val serviceRequestBtn = findViewById<Button>(R.id. )
        //serviceRequestBtn.setOnClickListener{

        //val serviceRequest= Intent(this,"fill in your class name")
        //startActivity(serviceRequest)
    //}
    }
}