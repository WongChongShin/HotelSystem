package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.database.FirebaseDatabase

class AddNewCustomer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_customer)

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Customer List");

        val btnAddCustomerBack: ImageView = findViewById<ImageView>(R.id.add_customer_back_logo);
        val btnAddCustomer = findViewById<Button>(R.id.add_customer_button)
        val btnCancelAddCustomer = findViewById<Button>(R.id.cancel_add_customer_button)

        btnAddCustomerBack.setOnClickListener{

            val addCustomerBackIntent = Intent(this, checkIn_and_checkOut::class.java)
            startActivity(addCustomerBackIntent)
        }

        btnAddCustomer.setOnClickListener{

            val room_no:String = findViewById<TextView>(R.id.add_room).text.toString()
            val name:String = findViewById<TextView>(R.id.add_name).text.toString()
            val email:String = findViewById<TextView>(R.id.add_email).text.toString()
            val phone:String = findViewById<TextView>(R.id.add_phone).text.toString()
            val check_in:String = findViewById<TextView>(R.id.add_checkIn).text.toString()
            val check_out:String = findViewById<TextView>(R.id.add_checkOut).text.toString()

            myRef.child(room_no).child("Room_No").setValue(room_no)
            myRef.child(room_no).child("Name").setValue(name)
            myRef.child(room_no).child("Email").setValue(email)
            myRef.child(room_no).child("Phone").setValue(phone)
            myRef.child(room_no).child("Check In").setValue(check_in)
            myRef.child(room_no).child("Check Out").setValue(check_out)

            val addCustomerIntent = Intent(this, checkIn_and_checkOut::class.java)
            startActivity(addCustomerIntent)
        }

        btnCancelAddCustomer.setOnClickListener{

            val cancelAddCustomerIntent = Intent(this, checkIn_and_checkOut::class.java)
            startActivity(cancelAddCustomerIntent)
        }
    }
}