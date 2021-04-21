package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.database.FirebaseDatabase

class edit_food_service : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_food_service)

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Food List")

        //back button
        val purchaseBackBtn: ImageView = findViewById<ImageView>(R.id.edit_food_back_button)
        purchaseBackBtn.setOnClickListener{
            val back = Intent(this, food_list::class.java)
            startActivity(back)
        }

        /*//cancel the edit
        val btnCancelEdit:Button = findViewById(R.id.cancel_food_edit_button)

        btnCancelEdit.setOnClickListener{

            val back2 = Intent(this, food_list::class.java)
            startActivity(back2)
        }*/

        //retrieve room no. to edit layout

        val editFoodRoom = findViewById<TextView>(R.id.edit_food_room)
        val foodRoom = intent?.getStringExtra("fRoom")

        editFoodRoom.setText(foodRoom)

        //retrieve name to edit layout
        val editFoodName = findViewById<TextView>(R.id.edit_food_name)
        val foodName = intent?.getStringExtra("fName")

        editFoodName.setText(foodName)

        //retrieve phone to edit layout
        val editFoodPhone = findViewById<TextView>(R.id.edit_food_phone)
        val foodPhone = intent?.getStringExtra("fPhone")

        editFoodPhone.setText(foodPhone)

        //confirm the edit food service list
        val btnEditFood = findViewById<Button>(R.id.confirm_food_button)

        btnEditFood.setOnClickListener() {
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("Food List")

            val editR:String = findViewById<TextView>(R.id.edit_food_room).text.toString()
            val editN:String = findViewById<TextView>(R.id.edit_food_name).text.toString()
            val editP:String = findViewById<TextView>(R.id.edit_food_phone).text.toString()

            myRef.child(editR).child("fRoom").setValue(editR)
            myRef.child(editR).child("fName").setValue(editN)
            myRef.child(editR).child("fPhone").setValue(editP)

        }

    }
}