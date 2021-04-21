package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.database.FirebaseDatabase

class EditCustomerList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_customer_list)

        val btnEditCustomerBack: ImageView = findViewById<ImageView>(R.id.edit_customer_back_logo);
        val btnEditCustomer = findViewById<Button>(R.id.edit_customer_button)
        val btnCancelEditCustomer = findViewById<Button>(R.id.cancel_edit_customer_button)

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Customer List")

        btnEditCustomerBack.setOnClickListener {
            val editCustomerBackIntent = Intent(this, checkIn_and_checkOut::class.java)
            startActivity(editCustomerBackIntent)
        }


        val editCustomerRoom = findViewById<TextView>(R.id.edit_room)
        val customerRoom = intent?.getStringExtra("Room_No")

        editCustomerRoom.setText(customerRoom)

        val editCustomerName = findViewById<TextView>(R.id.edit_name)
        val customerName = intent?.getStringExtra("Name")

        editCustomerName.setText(customerName)

        val editCustomerEmail = findViewById<TextView>(R.id.edit_email)
        val customerEmail = intent?.getStringExtra("Email")

        editCustomerEmail.setText(customerEmail)

        val editCustomerPhone = findViewById<TextView>(R.id.edit_phone)
        val customerPhone = intent?.getStringExtra("Phone")

        editCustomerPhone.setText(customerPhone)

        val editCustomeCheckIn = findViewById<TextView>(R.id.edit_checkIn)
        val customerCheckIn = intent?.getStringExtra("Check In")

        editCustomeCheckIn.setText(customerCheckIn)

        val editCustomeCheckOut = findViewById<TextView>(R.id.edit_checkOut)
        val customerCheckOut = intent?.getStringExtra("Check Out")

        editCustomeCheckOut.setText(customerCheckOut)

        btnEditCustomer.setOnClickListener {

            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("Customer List")

            val editCustomerRoom: String = findViewById<TextView>(R.id.edit_room).text.toString()
            val editName: String = findViewById<TextView>(R.id.edit_name).text.toString()
            val editEmail: String = findViewById<TextView>(R.id.edit_email).text.toString()
            val editPhone: String = findViewById<TextView>(R.id.edit_phone).text.toString()
            val editCheckIn: String = findViewById<TextView>(R.id.edit_checkIn).text.toString()
            val editCheckOut: String = findViewById<TextView>(R.id.edit_checkOut).text.toString()

            myRef.child(editCustomerRoom).child("Room_No").setValue(editCustomerRoom)
            myRef.child(editCustomerRoom).child("Name").setValue(editName)
            myRef.child(editCustomerRoom).child("Email").setValue(editEmail)
            myRef.child(editCustomerRoom).child("Phone").setValue(editPhone)
            myRef.child(editCustomerRoom).child("Check In").setValue(editCheckIn)
            myRef.child(editCustomerRoom).child("Check Out").setValue(editCheckOut)

            val editCustomerIntent = Intent(this, checkIn_and_checkOut::class.java)
            startActivity(editCustomerIntent)
        }

        btnCancelEditCustomer.setOnClickListener{
            val cancelEditCustomerIntent = Intent(this, checkIn_and_checkOut::class.java)
            startActivity(cancelEditCustomerIntent)
        }
    }
}