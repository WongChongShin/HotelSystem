package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.database.FirebaseDatabase

class EditPoolList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_pool_list)

        val btnEditPoolBack: ImageView = findViewById<ImageView>(R.id.edit_pool_reservation_back_logo);
        val btnEditPool = findViewById<Button>(R.id.edit_pool_list_button)
        val btnCancelEditPool = findViewById<Button>(R.id.cancel_edit_pool_list_button)

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Pool List")

        val editPoolRoom = findViewById<TextView>(R.id.edit_pool_list_room)
        val poolRoom = intent?.getStringExtra("Room No")

        editPoolRoom.setText(poolRoom)

        val editPoolPhone = findViewById<TextView>(R.id.edit_pool_list_phone)
        val poolPhone = intent?.getStringExtra("Phone")

        editPoolPhone.setText(poolPhone)

        val editPoolTime = findViewById<TextView>(R.id.edit_pool_list_time)
        val poolTime = intent?.getStringExtra("Time")

        editPoolTime.setText(poolTime)

        btnEditPoolBack.setOnClickListener{
            val editPoolBackIntent = Intent(this, IndoorSwimmingPool::class.java)
            startActivity(editPoolBackIntent)
        }

        btnEditPool.setOnClickListener{
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("Pool List")

            val editPoolRoom: String = findViewById<TextView>(R.id.edit_pool_list_room).text.toString()
            val editPoolPhone: String = findViewById<TextView>(R.id.edit_pool_list_phone).text.toString()
            val editPoolTime: String = findViewById<TextView>(R.id.edit_pool_list_time).text.toString()

            myRef.child(editPoolRoom).child("Room No").setValue(editPoolRoom)
            myRef.child(editPoolRoom).child("Phone").setValue(editPoolPhone)
            myRef.child(editPoolRoom).child("Time").setValue(editPoolTime)

            val editPoolIntent = Intent(this, IndoorSwimmingPool::class.java)
            startActivity(editPoolIntent)
        }

        btnCancelEditPool.setOnClickListener{
            val cancelEditPoolIntent = Intent(this, IndoorSwimmingPool::class.java)
            startActivity(cancelEditPoolIntent)
        }
    }
}