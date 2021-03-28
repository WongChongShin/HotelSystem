package com.example.mobilehotelmanagementsystem

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class Customer_select_detail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_select_detail)






        val detailBackBtn: ImageView = findViewById<ImageView>(R.id.select_detail_back2);

        detailBackBtn.setOnClickListener{
            val backCustomerSelectRoom = Intent(this, Customer_select_room::class.java)
            startActivity(backCustomerSelectRoom)
        }

        //----------------------------check in calender----------------------
        val checkInCalenderBtn: ImageView = findViewById<ImageView>(R.id.customer_select_calender);

        checkInCalenderBtn.setOnClickListener{

            val customerCheckInCalender = Calendar.getInstance()
            val checkInYear = customerCheckInCalender.get(Calendar.YEAR)
            val checkInMonth = customerCheckInCalender.get(Calendar.MONTH)
            val checkInDay = customerCheckInCalender.get(Calendar.DAY_OF_MONTH)

            val checkInDatePick = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, day ->

                val checkInDate = findViewById<TextView>(R.id.customer_check_in)
                checkInDate.setText("" + day + "/" + month + "/" + year)

            }, checkInYear, checkInMonth, checkInDay)

            checkInDatePick.show()
        }

        //----------------------------check out calender----------------------
        val checkOutCalenderBtn: ImageView = findViewById<ImageView>(R.id.customer_select_calender2);

        checkOutCalenderBtn.setOnClickListener {

            val customerCheckOutCalender = Calendar.getInstance()
            val checkOutYear = customerCheckOutCalender.get(Calendar.YEAR)
            val checkOutMonth = customerCheckOutCalender.get(Calendar.MONTH)
            val checkOutDay = customerCheckOutCalender.get(Calendar.DAY_OF_MONTH)

            val checkOutDatePick = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, day ->


                val checkOutDate = findViewById<TextView>(R.id.customer_check_out)
                checkOutDate.setText("" + day + "/" + month + "/" + year)

            }, checkOutYear, checkOutMonth, checkOutDay)

            checkOutDatePick.show()
        }


        val detailNextBtn = findViewById<Button>(R.id.select_detail_nextBtn)

        detailNextBtn .setOnClickListener{

            val checkInDateValidate = findViewById<TextView>(R.id.customer_check_in)
            val checkOutDateValidate  = findViewById<TextView>(R.id.customer_check_out)
            validate(checkInDateValidate.getText().toString(),checkOutDateValidate.getText().toString());

        }



    }

    private fun validate(checkInDate:String, checkOutDate:String){
        if(checkInDate=="" || checkOutDate==""){
            val customerUsername = findViewById<TextView>(R.id.customer_error_message_2)
            customerUsername.setText("You are require to fill up all the blank ")
        }
        else{

            val customerDetailIntent = Intent(this, Customer_purchase::class.java)
            customerDetailIntent.putExtra("checkIn_date", checkInDate);
            customerDetailIntent.putExtra("checkOut_date", checkOutDate);
            startActivity(customerDetailIntent)
        }
    }
}