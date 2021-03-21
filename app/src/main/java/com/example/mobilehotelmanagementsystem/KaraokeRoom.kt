package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class KaraokeRoom : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_karaoke_room)

        val btnKaraokeBack: ImageView = findViewById<ImageView>(R.id.karaoke_page_back_logo);

        btnKaraokeBack.setOnClickListener{
            val checkBackIntent = Intent(this, facilities_monitoring::class.java)
            startActivity(checkBackIntent)
        }
    }
}