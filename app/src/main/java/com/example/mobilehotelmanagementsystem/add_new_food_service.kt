package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.database.FirebaseDatabase

class add_new_food_service : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_food_service)

        //Add and cancel
        val btnAddFood = findViewById<Button>(R.id.add_food_button)

        btnAddFood.setOnClickListener(){

            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("Food List")

            val roomNo:String = findViewById<TextView>(R.id.add_food_room).text.toString()
            val name:String = findViewById<TextView>(R.id.add_food_name).text.toString()
            val phone:String = findViewById<TextView>(R.id.add_food_phone).text.toString()

            myRef.child(roomNo).child("fRoom").setValue(roomNo)
            myRef.child(roomNo).child("fName").setValue(name)
            myRef.child(roomNo).child("fPhone").setValue(phone)

            val addfoodIntent = Intent(this, food_list::class.java)

            startActivity(addfoodIntent)
        }

        val btnCancelFood = findViewById<Button>(R.id.cancel_food_button)

        btnCancelFood.setOnClickListener(){

            val foodListIntent = Intent(this, food_list::class.java)
            startActivity(foodListIntent)
        }
    }
}