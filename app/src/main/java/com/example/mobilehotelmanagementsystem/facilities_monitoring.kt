package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class facilities_monitoring : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facilities_monitoring)

        val btnKaraoke = findViewById<Button>(R.id.karaoke_button)
        val btnMassage = findViewById<Button>(R.id.massage_button)
        val btnPool = findViewById<Button>(R.id.indoor_swimming_pool_button)

        btnKaraoke.setOnClickListener{

            val karaokeIntent = Intent(this, KaraokeRoom::class.java)
            startActivity(karaokeIntent)
        }

        btnMassage.setOnClickListener{

            val roomServiceIntent = Intent(this, MassageRoom::class.java)
            startActivity(roomServiceIntent)
        }

        btnPool.setOnClickListener{

            val facilityIntent = Intent(this, IndoorSwimmingPool::class.java)
            startActivity(facilityIntent)
        }
    }
}