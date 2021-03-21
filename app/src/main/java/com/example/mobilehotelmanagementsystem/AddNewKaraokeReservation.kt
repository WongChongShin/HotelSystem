package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class AddNewKaraokeReservation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_karaoke_reservation)

        val btnAddKaraokeBack: ImageView = findViewById<ImageView>(R.id.add_karaoke_reservation_back_logo);
        val btnAddKaraoke = findViewById<Button>(R.id.add_karaoke_reservation_button)
        val btnCancelAddKaraoke = findViewById<Button>(R.id.cancel_add_karaoke_reservation_button)

        btnAddKaraokeBack.setOnClickListener{
            val addKaraokeBackIntent = Intent(this, KaraokeRoom::class.java)
            startActivity(addKaraokeBackIntent)
        }

        btnAddKaraoke.setOnClickListener{

            val addKaraokeIntent = Intent(this, KaraokeRoom::class.java)
            startActivity(addKaraokeIntent)
        }

        btnCancelAddKaraoke.setOnClickListener{

            val cancelAddKaraokeIntent = Intent(this, KaraokeRoom::class.java)
            startActivity(cancelAddKaraokeIntent)
        }
    }
}