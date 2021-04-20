package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.database.FirebaseDatabase

class edit_cleaning_service : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_cleaning_service)

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Cleaning List")

        /*//back button
        val purchaseBackBtn: ImageView = findViewById<ImageView>(R.id.edit_clean_back_button);
        purchaseBackBtn.setOnClickListener{
            val back = Intent(this, cleaning_list::class.java)
            startActivity(back)
        }*/

        //retrieve room no. to edit layout

            val editCleanRoom = findViewById<TextView>(R.id.edit_clean_room)
            val cleanRoom = intent?.getStringExtra("Room")

            editCleanRoom.setText(cleanRoom)

            //retrieve name to edit layout
            val editCleanName = findViewById<TextView>(R.id.edit_clean_name)
            val cleanName = intent?.getStringExtra("Name")

            editCleanName.setText(cleanName)

            //retrieve phone to edit layout
            val editCleanPhone = findViewById<TextView>(R.id.edit_clean_phone)
            val cleanPhone = intent?.getStringExtra("Phone")

            editCleanPhone.setText(cleanPhone)

        //confirm the edit clean service list
        val btnEditClean = findViewById<Button>(R.id.confirm_clean_edit_button)

        btnEditClean.setOnClickListener() {
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("Cleaning List")

            val editR:String = findViewById<TextView>(R.id.edit_clean_room).text.toString()
            val editN:String = findViewById<TextView>(R.id.edit_clean_name).text.toString()
            val editP:String = findViewById<TextView>(R.id.edit_clean_phone).text.toString()

            myRef.child(editR).child("Room").setValue(editR)
            myRef.child(editR).child("Name").setValue(editN)
            myRef.child(editR).child("Phone").setValue(editP)

        }



        //cancel the edit
        /*val btnCancelEdit = findViewById<Button>(R.id.cancel_edit_button)

        btnCancelEdit.setOnClickListener(){

            val cleanListIntent = Intent(this, cleaning_list::class.java)
            startActivity(cleanListIntent)
        }*/

    }
}