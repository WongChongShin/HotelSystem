package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.database.FirebaseDatabase

class AddNewPoolReservation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_pool_reservation)

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Pool List");

        val btnAddPoolBack: ImageView = findViewById<ImageView>(R.id.add_pool_reservation_back_logo);
        val btnAddPool = findViewById<Button>(R.id.add_pool_reservation_button)
        val btnCancelAddPool = findViewById<Button>(R.id.cancel_add_pool_reservation_button)

        btnAddPoolBack.setOnClickListener{
            val addPoolBackIntent = Intent(this, IndoorSwimmingPool::class.java)
            startActivity(addPoolBackIntent)
        }

        btnAddPool.setOnClickListener{
            val room_no:String = findViewById<TextView>(R.id.add_pool_reservation_room).text.toString()
            val phone:String = findViewById<TextView>(R.id.add_pool_reservation_phone).text.toString()
            val time:String = findViewById<TextView>(R.id.add_pool_reservation_time).text.toString()

            myRef.child(room_no).child("Room No").setValue(room_no)
            myRef.child(room_no).child("Phone").setValue(phone)
            myRef.child(room_no).child("Time").setValue(time)

            val addPoolIntent = Intent(this, IndoorSwimmingPool::class.java)

            startActivity(addPoolIntent)
        }

        btnCancelAddPool.setOnClickListener{

            val cancelAddPoolIntent = Intent(this, IndoorSwimmingPool::class.java)
            startActivity(cancelAddPoolIntent)
        }
    }
}