package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class main_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        val username = intent?.getStringExtra("username")
        val signInUsername = findViewById<TextView>(R.id.main_page_username)

        signInUsername.setText(username)

        val btnCheck = findViewById<Button>(R.id.check_button)
        val btnRoom = findViewById<Button>(R.id.room_button)
        val btnFacility = findViewById<Button>(R.id.facility_button)

        btnCheck.setOnClickListener{

            val checkInAndCheckOutIntent = Intent(this, checkIn_and_checkOut::class.java)
            startActivity(checkInAndCheckOutIntent)
        }

        btnRoom.setOnClickListener{

           val roomServiceIntent = Intent(this, rooms_service::class.java)
            startActivity(roomServiceIntent)
        }

        btnFacility.setOnClickListener{

            val facilityIntent = Intent(this, facilities_monitoring::class.java)
            startActivity(facilityIntent)
        }
    }
}