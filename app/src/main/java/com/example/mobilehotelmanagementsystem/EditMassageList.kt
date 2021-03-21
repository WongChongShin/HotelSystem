package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class EditMassageList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_massage_list)

        val btnEditMassageBack: ImageView = findViewById<ImageView>(R.id.edit_massage_reservation_back_logo);
        val btnEditMassage = findViewById<Button>(R.id.edit_massage_list_button)
        val btnCancelEditMassage = findViewById<Button>(R.id.cancel_edit_massage_list_button)

        btnEditMassageBack.setOnClickListener{
            val editMassageBackIntent = Intent(this, MassageRoom::class.java)
            startActivity(editMassageBackIntent)
        }

        btnEditMassage.setOnClickListener{
            val editMassageIntent = Intent(this, MassageRoom::class.java)
            startActivity(editMassageIntent)
        }

        btnCancelEditMassage.setOnClickListener{
            val cancelEditMassageIntent = Intent(this, MassageRoom::class.java)
            startActivity(cancelEditMassageIntent)
        }
    }
}