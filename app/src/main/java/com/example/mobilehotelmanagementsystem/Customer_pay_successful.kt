package com.example.mobilehotelmanagementsystem

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Customer_pay_successful : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_pay_success)


        val mainMenuBtn= findViewById<Button>(R.id.customer_main_menu_btn);

        mainMenuBtn.setOnClickListener{
            val goMainMenu = Intent(this, CustomerMainActivity::class.java)
            startActivity(goMainMenu)
        }




    }
}
