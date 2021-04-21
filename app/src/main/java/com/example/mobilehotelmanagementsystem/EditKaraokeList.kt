package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.database.FirebaseDatabase

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

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Karaoke List")

        val editKaraokeRoom = findViewById<TextView>(R.id.edit_karaoke_list_room)
        val karaokeRoom = intent?.getStringExtra("Room No")

        editKaraokeRoom.setText(karaokeRoom)

        val editKaraokePhone = findViewById<TextView>(R.id.edit_karaoke_list_phone)
        val karaokePhone = intent?.getStringExtra("Phone")

        editKaraokePhone.setText(karaokePhone)

        val editKaraokeTime = findViewById<TextView>(R.id.edit_karaoke_list_time)
        val karaokeTime = intent?.getStringExtra("Time")

        editKaraokeTime.setText(karaokeTime)

        btnEditKaraoke.setOnClickListener{
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("Karaoke List")

            val editKaraokeRoom: String = findViewById<TextView>(R.id.edit_karaoke_list_room).text.toString()
            val editKaraokePhone: String = findViewById<TextView>(R.id.edit_karaoke_list_phone).text.toString()
            val editKaraokeTime: String = findViewById<TextView>(R.id.edit_karaoke_list_time).text.toString()

            myRef.child(editKaraokeRoom).child("Room No").setValue(editKaraokeRoom)
            myRef.child(editKaraokeRoom).child("Phone").setValue(editKaraokePhone)
            myRef.child(editKaraokeRoom).child("Time").setValue(editKaraokeTime)

            val editKaraokeIntent = Intent(this, KaraokeRoom::class.java)
            startActivity(editKaraokeIntent)
        }

        btnCancelEditKaraoke.setOnClickListener{
            val cancelEditKaraokeIntent = Intent(this, KaraokeRoom::class.java)
            startActivity(cancelEditKaraokeIntent)
        }
    }
}