package com.example.mobilehotelmanagementsystem

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

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
            if(password.length<8){
                val customerUsername = findViewById<TextView>(R.id.customer_error_message)
                customerUsername.setText("Your password need more than 8 word")
            }
            else {
                val customerSignInIntent = Intent(this, CustomerMainActivity::class.java)

                customerSignInIntent.putExtra("username", userName.toString())

                val toast = Toast.makeText(applicationContext, "Login Successful", Toast.LENGTH_SHORT)
                toast.show()
                GlobalScope.async {
                    delay(800)
                    startActivity(customerSignInIntent)
                }


            }
        }
    }
}