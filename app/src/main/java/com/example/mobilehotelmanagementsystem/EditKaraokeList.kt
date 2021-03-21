package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class EditKaraokeList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_karaoke_list)

        val btnEditKaraokeBack: ImageView = findViewById<ImageView>(R.id.edit_karaoke_reservation_back_logo);
        val btnEditKaraoke = findViewById<Button>(R.id.edit_karaoke_list_button)
        val btnCancelEditKaraoke = findViewById<Button>(R.id.cancel_edit_karaoke_list_button)

        btnEditKaraokeBack.setOnClickListener{
            val editKaraokeBackIntent = Intent(this, KaraokeRoom::class.java)
            startActivity(editKaraokeBackIntent)
        }

        btnEditKaraoke.setOnClickListener{
            val editKaraokeIntent = Intent(this, KaraokeRoom::class.java)
            startActivity(editKaraokeIntent)
        }

        btnCancelEditKaraoke.setOnClickListener{
            val cancelEditKaraokeIntent = Intent(this, KaraokeRoom::class.java)
            startActivity(cancelEditKaraokeIntent)
        }
    }
}