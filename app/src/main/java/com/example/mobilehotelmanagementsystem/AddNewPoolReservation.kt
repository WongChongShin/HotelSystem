package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class AddNewPoolReservation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_pool_reservation)

        val btnAddPoolBack: ImageView = findViewById<ImageView>(R.id.add_pool_reservation_back_logo);
        val btnAddPool = findViewById<Button>(R.id.add_pool_reservation_button)
        val btnCancelAddPool = findViewById<Button>(R.id.cancel_add_pool_reservation_button)

        btnAddPoolBack.setOnClickListener{
            val addPoolBackIntent = Intent(this, IndoorSwimmingPool::class.java)
            startActivity(addPoolBackIntent)
        }

        btnAddPool.setOnClickListener{

            val addPoolIntent = Intent(this, IndoorSwimmingPool::class.java)
            startActivity(addPoolIntent)
        }

        btnCancelAddPool.setOnClickListener{

            val cancelAddPoolIntent = Intent(this, IndoorSwimmingPool::class.java)
            startActivity(cancelAddPoolIntent)
        }
    }
}