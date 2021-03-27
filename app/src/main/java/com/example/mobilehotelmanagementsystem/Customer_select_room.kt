package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class Customer_select_room : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_select_room)

        val roomBackBtn: ImageView = findViewById<ImageView>(R.id.select_room_back);

        roomBackBtn.setOnClickListener{
            val backCustomerMain = Intent(this, CustomerMainActivity::class.java)
            startActivity(backCustomerMain)
        }



    }
}