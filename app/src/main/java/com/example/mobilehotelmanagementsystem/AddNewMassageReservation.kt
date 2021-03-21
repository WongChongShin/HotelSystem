package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class AddNewMassageReservation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_massage_reservation)

        val btnAddMassageBack: ImageView = findViewById<ImageView>(R.id.add_massage_reservation_back_logo);
        val btnAddMassage = findViewById<Button>(R.id.add_massage_reservation_button)
        val btnCancelAddMassage = findViewById<Button>(R.id.cancel_add_massage_reservation_button)

        btnAddMassageBack.setOnClickListener{
            val addMassageBackIntent = Intent(this, MassageRoom::class.java)
            startActivity(addMassageBackIntent)
        }

        btnAddMassage.setOnClickListener{

            val addMassageIntent = Intent(this, MassageRoom::class.java)
            startActivity(addMassageIntent)
        }

        btnCancelAddMassage.setOnClickListener{

            val cancelAddMassageIntent = Intent(this, MassageRoom::class.java)
            startActivity(cancelAddMassageIntent)
        }
    }
}