package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class Customer_select_detail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_select_detail)

        val detailBackBtn: ImageView = findViewById<ImageView>(R.id.customer_select_calender);

        detailBackBtn.setOnClickListener{
            val backCustomerRoom= Intent(this, Customer_select_room::class.java)
            startActivity(backCustomerRoom)
        }

        val checkInCalenderBtn: ImageView = findViewById<ImageView>(R.id.customer_select_calender);

        checkInCalenderBtn.setOnClickListener{
            val goCustomerCalender= Intent(this, customer_calender::class.java)
            startActivity(goCustomerCalender)
        }


        //val detailNextBtn = findViewById<Button>(R.id.select_detail_nextBtn)

        //detailNextBtn .setOnClickListener{

            //val customerDetailIntent = Intent(this, Customer_purchase::class.java)
            //val customerCheckIn = findViewById<TextView>(R.id.customer_check_in_input).text
            //val customerCheckOut = findViewById<TextView>(R.id.customer_check_out_input).text
            //customerDetailIntent.putExtra("checkIn_date", customerCheckIn.toString());
            //customerDetailIntent.putExtra("checkOut_date", customerCheckOut.toString());
            //startActivity(customerDetailIntent)
       // }



    }
}