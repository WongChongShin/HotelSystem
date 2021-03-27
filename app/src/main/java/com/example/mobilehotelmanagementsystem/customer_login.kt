package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class customer_login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_login)


        val customerLoginBtn = findViewById<Button>(R.id.customer_sign_in_button)

        customerLoginBtn.setOnClickListener{

            val customerSignInIntent = Intent(this, CustomerMainActivity::class.java)
            val customerUsername = findViewById<TextView>(R.id.customer_sign_in_username).text
            customerSignInIntent.putExtra("username", customerUsername.toString());
            startActivity(customerSignInIntent)
        }
    }
}