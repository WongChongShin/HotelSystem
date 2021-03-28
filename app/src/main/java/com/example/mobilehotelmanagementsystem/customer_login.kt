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

            val customerUsername = findViewById<TextView>(R.id.customer_sign_in_username).text
            val customerPassword= findViewById<TextView>(R.id.customer_sign_in_password).text
            validate(customerUsername.toString(),customerPassword.toString());

        }
    }
    private fun validate(userName:String, password:String){
        if(userName=="" || password==""){
            val customerUsername = findViewById<TextView>(R.id.customer_error_message)
            customerUsername.setText("You are require to fill up all the blank ")
        }
        else{
            val customerSignInIntent = Intent(this, CustomerMainActivity::class.java)

            customerSignInIntent.putExtra("username", userName.toString())
            startActivity(customerSignInIntent)
        }
    }
}