package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class EditPoolList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_pool_list)

        val btnEditPoolBack: ImageView = findViewById<ImageView>(R.id.edit_pool_reservation_back_logo);
        val btnEditPool = findViewById<Button>(R.id.edit_pool_list_button)
        val btnCancelEditPool = findViewById<Button>(R.id.cancel_edit_pool_list_button)

        btnEditPoolBack.setOnClickListener{
            val editPoolBackIntent = Intent(this, IndoorSwimmingPool::class.java)
            startActivity(editPoolBackIntent)
        }

        btnEditPool.setOnClickListener{
            val editPoolIntent = Intent(this, IndoorSwimmingPool::class.java)
            startActivity(editPoolIntent)
        }

        btnCancelEditPool.setOnClickListener{
            val cancelEditPoolIntent = Intent(this, IndoorSwimmingPool::class.java)
            startActivity(cancelEditPoolIntent)
        }
    }
}