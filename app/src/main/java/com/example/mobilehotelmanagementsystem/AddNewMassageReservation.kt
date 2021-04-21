package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.database.FirebaseDatabase

class AddNewMassageReservation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_massage_reservation)

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Massage List");

        val btnAddMassageBack: ImageView = findViewById<ImageView>(R.id.add_massage_reservation_back_logo);
        val btnAddMassage = findViewById<Button>(R.id.add_massage_reservation_button)
        val btnCancelAddMassage = findViewById<Button>(R.id.cancel_add_massage_reservation_button)

        btnAddMassageBack.setOnClickListener{
            val addMassageBackIntent = Intent(this, MassageRoom::class.java)
            startActivity(addMassageBackIntent)
        }

        btnAddMassage.setOnClickListener{

            val room_no:String = findViewById<TextView>(R.id.add_massage_reservation_room).text.toString()
            val phone:String = findViewById<TextView>(R.id.add_massage_reservation_phone).text.toString()
            val time:String = findViewById<TextView>(R.id.add_massage_reservation_time).text.toString()

            myRef.child(room_no).child("Room No").setValue(room_no)
            myRef.child(room_no).child("Phone").setValue(phone)
            myRef.child(room_no).child("Time").setValue(time)

            val addMassageIntent = Intent(this, MassageRoom::class.java)
            startActivity(addMassageIntent)
        }

        btnCancelAddMassage.setOnClickListener{

            val cancelAddMassageIntent = Intent(this, MassageRoom::class.java)
            startActivity(cancelAddMassageIntent)
        }
    }
}