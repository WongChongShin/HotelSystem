package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.database.FirebaseDatabase

class add_new_cleaning_service : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_cleaning_service)

        val btnAddClean = findViewById<Button>(R.id.add_clean_button)

        btnAddClean.setOnClickListener(){

            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("Cleaning List")

            val roomNo:String = findViewById<TextView>(R.id.add_clean_room).text.toString()
            val name:String = findViewById<TextView>(R.id.add_clean_name).text.toString()
            val phone:String = findViewById<TextView>(R.id.add_clean_phone).text.toString()

            myRef.child(roomNo).child("Name").setValue(name)
            myRef.child(roomNo).child("Phone").setValue(phone)
        }

        val btnCancelClean = findViewById<Button>(R.id.cancel_clean_button)

        btnCancelClean.setOnClickListener(){

            val cleanListIntent = Intent(this, cleaning_list::class.java)
            startActivity(cleanListIntent)
        }
    }
}