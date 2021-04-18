package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Customer_select_service : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_select_service)

        val foodServiceBtn: Button = findViewById(R.id.select_food_service_btn)

        //initialize food service button
        foodServiceBtn.setOnClickListener(){
            val foodService= Intent(this, Customer_select_food_service::class.java)
            startActivity(foodService)

        }

    }



}