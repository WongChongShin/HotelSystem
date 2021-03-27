package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Select_staff_customer_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_customer_staff_page)


        val customerBtn = findViewById<Button>(R.id.customer_button)
        customerBtn.setOnClickListener{

            val customerLogin = Intent(this,customer_login::class.java)
            startActivity(customerLogin)
        }

        val staffBtn = findViewById<Button>(R.id.staff_button)
        staffBtn.setOnClickListener{

            val staffLogin = Intent(this,MainActivity::class.java)
            startActivity(staffLogin)
        }

    }
}