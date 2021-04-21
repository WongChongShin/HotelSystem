package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.database.FirebaseDatabase

class AddNewKaraokeReservation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_karaoke_reservation)

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Karaoke List");

        val btnAddKaraokeBack: ImageView = findViewById<ImageView>(R.id.add_karaoke_reservation_back_logo);
        val btnAddKaraoke = findViewById<Button>(R.id.add_karaoke_reservation_button)
        val btnCancelAddKaraoke = findViewById<Button>(R.id.cancel_add_karaoke_reservation_button)

        btnAddKaraokeBack.setOnClickListener{
            val addKaraokeBackIntent = Intent(this, KaraokeRoom::class.java)
            startActivity(addKaraokeBackIntent)
        }

        btnAddKaraoke.setOnClickListener{

            val room_no:String = findViewById<TextView>(R.id.add_karaoke_reservation_room).text.toString()
            val phone:String = findViewById<TextView>(R.id.add_karaoke_reservation_phone).text.toString()
            val time:String = findViewById<TextView>(R.id.add_karaoke_reservation_time).text.toString()

            myRef.child(room_no).child("Room No").setValue(room_no)
            myRef.child(room_no).child("Phone").setValue(phone)
            myRef.child(room_no).child("Time").setValue(time)

            val addKaraokeIntent = Intent(this, KaraokeRoom::class.java)
            startActivity(addKaraokeIntent)
        }

        btnCancelAddKaraoke.setOnClickListener{

            val cancelAddKaraokeIntent = Intent(this, KaraokeRoom::class.java)
            startActivity(cancelAddKaraokeIntent)
        }
    }
}