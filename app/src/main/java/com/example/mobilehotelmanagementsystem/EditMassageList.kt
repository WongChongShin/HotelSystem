package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.database.FirebaseDatabase

class EditMassageList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_massage_list)

        val btnEditMassageBack: ImageView = findViewById<ImageView>(R.id.edit_massage_reservation_back_logo);
        val btnEditMassage = findViewById<Button>(R.id.edit_massage_list_button)
        val btnCancelEditMassage = findViewById<Button>(R.id.cancel_edit_massage_list_button)

        btnEditMassageBack.setOnClickListener{
            val editMassageBackIntent = Intent(this, MassageRoom::class.java)
            startActivity(editMassageBackIntent)
        }

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Massage List")

        val editMassageRoom = findViewById<TextView>(R.id.edit_massage_list_room)
        val massageRoom = intent?.getStringExtra("Room No")

        editMassageRoom.setText(massageRoom)

        val editMassagePhone = findViewById<TextView>(R.id.edit_massage_list_phone)
        val massagePhone = intent?.getStringExtra("Phone")

        editMassagePhone.setText(massagePhone)

        val editMassageTime = findViewById<TextView>(R.id.edit_massage_list_time)
        val massageTime = intent?.getStringExtra("Time")

        editMassageTime.setText(massageTime)

        btnEditMassage.setOnClickListener{
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("Massage List")

            val editMassageRoom: String = findViewById<TextView>(R.id.edit_massage_list_room).text.toString()
            val editMassagePhone: String = findViewById<TextView>(R.id.edit_massage_list_phone).text.toString()
            val editMassageTime: String = findViewById<TextView>(R.id.edit_massage_list_time).text.toString()

            myRef.child(editMassageRoom).child("Room No").setValue(editMassageRoom)
            myRef.child(editMassageRoom).child("Phone").setValue(editMassagePhone)
            myRef.child(editMassageRoom).child("Time").setValue(editMassageTime)

            val editMassageIntent = Intent(this, MassageRoom::class.java)
            startActivity(editMassageIntent)
        }


        btnCancelEditMassage.setOnClickListener{
            val cancelEditMassageIntent = Intent(this, MassageRoom::class.java)
            startActivity(cancelEditMassageIntent)
        }
    }
}