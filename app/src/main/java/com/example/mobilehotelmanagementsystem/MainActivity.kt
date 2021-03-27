package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLogin = findViewById<Button>(R.id.customer_sign_in_button)

        btnLogin.setOnClickListener{

            val signInIntent = Intent(this, main_page::class.java)
            val username = findViewById<TextView>(R.id.customer_sign_in_username).text
            signInIntent.putExtra("username", username.toString());
            startActivity(signInIntent)
        }
    }
}