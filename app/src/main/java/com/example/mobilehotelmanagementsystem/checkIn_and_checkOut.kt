package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class checkIn_and_checkOut : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_in_and_check_out)

        val btnCheckAdd: ImageView = findViewById<ImageView>(R.id.add_check_logo);
        val btnCheckBack: ImageView = findViewById<ImageView>(R.id.customer_page_back_logo);

        btnCheckAdd.setOnClickListener{
            val checkAddIntent = Intent(this, AddNewCustomer::class.java)
            startActivity(checkAddIntent)
        }

        btnCheckBack.setOnClickListener{
                val checkBackIntent = Intent(this, main_page::class.java)
                startActivity(checkBackIntent)
        }
    }
}