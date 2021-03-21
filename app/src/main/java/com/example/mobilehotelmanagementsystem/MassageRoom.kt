package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MassageRoom : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_massage_room)

        val btnMassageBack: ImageView = findViewById<ImageView>(R.id.massage_page_back_logo);

        btnMassageBack.setOnClickListener{
            val checkBackIntent = Intent(this, facilities_monitoring::class.java)
            startActivity(checkBackIntent)
        }
    }
}