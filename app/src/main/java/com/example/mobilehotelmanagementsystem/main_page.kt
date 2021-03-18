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

        val btnRoom = findViewById<Button>(R.id.room_button)

        btnRoom.setOnClickListener{

           val roomServiceIntent = Intent(this, rooms_service::class.java)
            startActivity(roomServiceIntent)
        }
    }
}