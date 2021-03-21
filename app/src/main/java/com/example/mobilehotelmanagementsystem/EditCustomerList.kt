package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class EditCustomerList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_customer_list)

        val btnEditCustomerBack: ImageView = findViewById<ImageView>(R.id.edit_customer_back_logo);
        val btnEditCustomer = findViewById<Button>(R.id.edit_customer_button)
        val btnCancelEditCustomer = findViewById<Button>(R.id.cancel_edit_customer_button)

        btnEditCustomerBack.setOnClickListener{
            val editCustomerBackIntent = Intent(this, checkIn_and_checkOut::class.java)
            startActivity(editCustomerBackIntent)
        }

        btnEditCustomer.setOnClickListener{
            val editCustomerIntent = Intent(this, checkIn_and_checkOut::class.java)
            startActivity(editCustomerIntent)
        }

        btnCancelEditCustomer.setOnClickListener{
            val cancelEditCustomerIntent = Intent(this, checkIn_and_checkOut::class.java)
            startActivity(cancelEditCustomerIntent)
        }
    }
}