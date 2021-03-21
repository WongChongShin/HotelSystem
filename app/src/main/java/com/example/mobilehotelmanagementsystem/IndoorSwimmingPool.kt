package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class IndoorSwimmingPool : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_indoor_swimming_pool)

        val btnPoolBack: ImageView = findViewById<ImageView>(R.id.pool_page_back_logo);

        btnPoolBack.setOnClickListener{
            val checkBackIntent = Intent(this, facilities_monitoring::class.java)
            startActivity(checkBackIntent)
        }
    }
}