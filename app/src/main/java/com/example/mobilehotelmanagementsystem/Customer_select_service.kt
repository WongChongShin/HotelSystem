package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Customer_select_service : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_select_service)

        var currentUsername = ""
        var currentEmail = ""

        //get username and email
        currentUsername  =  intent?.getStringExtra("username") as String
        currentEmail  =  intent?.getStringExtra("email") as String



        val foodServiceBtn: Button = findViewById(R.id.select_food_service_btn)

        //initialize food service button
        foodServiceBtn.setOnClickListener(){
            val foodService= Intent(this, Customer_select_food_service::class.java)
            foodService.putExtra("username", currentUsername)
            foodService.putExtra("email", currentEmail)
            startActivity(foodService)

        }

    }



}