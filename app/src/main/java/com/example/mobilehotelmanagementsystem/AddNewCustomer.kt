package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class AddNewCustomer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_customer)

        val btnAddCustomerBack: ImageView = findViewById<ImageView>(R.id.add_customer_back_logo);
        val btnAddCustomer = findViewById<Button>(R.id.add_customer_button)
        val btnCancelAddCustomer = findViewById<Button>(R.id.cancel_add_customer_button)

        btnAddCustomerBack.setOnClickListener{
            val addCustomerBackIntent = Intent(this, checkIn_and_checkOut::class.java)
            startActivity(addCustomerBackIntent)
        }

        btnAddCustomer.setOnClickListener{

            val addCustomerIntent = Intent(this, checkIn_and_checkOut::class.java)
            startActivity(addCustomerIntent)
        }

        btnCancelAddCustomer.setOnClickListener{

            val cancelAddCustomerIntent = Intent(this, checkIn_and_checkOut::class.java)
            startActivity(cancelAddCustomerIntent)
        }
    }
}