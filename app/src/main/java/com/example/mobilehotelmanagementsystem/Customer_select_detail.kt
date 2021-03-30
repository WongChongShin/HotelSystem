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

        //----------------------------check in calender validation----------------------
        val customerCheckInCalender = Calendar.getInstance()
        val checkInYear = customerCheckInCalender.get(Calendar.YEAR)
        val checkInMonth = customerCheckInCalender.get(Calendar.MONTH)
        val checkInDay = customerCheckInCalender.get(Calendar.DAY_OF_MONTH)

        //----------------------------check out calender validation----------------------
        val customerCheckOutCalender = Calendar.getInstance()
        val checkOutYear = customerCheckOutCalender.get(Calendar.YEAR)
        val checkOutMonth = customerCheckOutCalender.get(Calendar.MONTH)
        val checkOutDay = customerCheckOutCalender.get(Calendar.DAY_OF_MONTH)


        //----------------------------check in calender----------------------
        val checkInCalenderBtn: ImageView = findViewById<ImageView>(R.id.customer_select_calender);
        checkInCalenderBtn.setOnClickListener{



            val checkInDatePick = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, day ->

                val checkInDate = findViewById<TextView>(R.id.customer_check_in)
                customerCheckInCalender.set(year, month, day);

                val checkInMonthText= month+1
                checkInDate.setText("" + day + "/" + checkInMonthText + "/" + year)

            }, checkInYear, checkInMonth, checkInDay)


            val nextDay = Calendar.getInstance()
            nextDay.add(Calendar.DATE,1)
            checkInDatePick.getDatePicker().setMinDate(nextDay.getTimeInMillis())

            checkInDatePick.show()
        }



            //----------------------------check out calender----------------------
            val checkOutCalenderBtn: ImageView = findViewById<ImageView>(R.id.customer_select_calender2);

            checkOutCalenderBtn.setOnClickListener {

                val checkingCheckInFill=findViewById<TextView>(R.id.customer_check_in).text.toString()

                if(checkingCheckInFill==""){
                    val checkInErrorMessage = findViewById<TextView>(R.id.customer_error_message_2)
                    checkInErrorMessage.setText("You need to choose check in date first ")
                }
                else {

                val checkOutDatePick = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, day ->


                    val checkOutDate = findViewById<TextView>(R.id.customer_check_out)

                    val checkOutMonthText = month + 1
                    checkOutDate.setText("" + day + "/" + checkOutMonthText + "/" + year)


                }, checkOutYear, checkOutMonth, checkOutDay)

                customerCheckInCalender.add(Calendar.DATE,1)
                checkOutDatePick.getDatePicker().setMinDate(customerCheckInCalender.getTimeInMillis())
                checkOutDatePick.show()

                val customerCheckInCalender = Calendar.getInstance()
                val checkInYear = customerCheckInCalender.get(Calendar.YEAR)
                val checkInMonth = customerCheckInCalender.get(Calendar.MONTH)
                val checkInDay = customerCheckInCalender.get(Calendar.DAY_OF_MONTH)
            }
        }



        //--------------------room no--------------
        val customerRoomNo = intent?.getStringExtra("room_no")

        //--------------------description--------------
        val customerDesc = intent?.getStringExtra("room_desc")


        //--------------------price--------------
        val customerPrice = intent?.getStringExtra("room_price")


        //-----------------------next button----------------------------------
        val detailNextBtn = findViewById<Button>(R.id.select_detail_nextBtn)

        detailNextBtn .setOnClickListener{

            val checkInDateValidate = findViewById<TextView>(R.id.customer_check_in)
            val checkOutDateValidate  = findViewById<TextView>(R.id.customer_check_out)
            validate(checkInDateValidate.getText().toString(),checkOutDateValidate.getText().toString(), customerRoomNo.toString(),customerDesc.toString(),customerPrice.toString());

        }




    }

    private fun validate(checkInDate:String, checkOutDate:String, customerRoomNo:String,customerDesc:String,customerPrice:String){
        if(checkInDate=="" || checkOutDate==""){
            val checkInErrorMessage = findViewById<TextView>(R.id.customer_error_message_2)
            checkInErrorMessage.setText("You are require to fill up all the blank ")
        }
        else{

            val customerDetailIntent = Intent(this, Customer_purchase::class.java)
            customerDetailIntent.putExtra("room_no", customerRoomNo.toString());
            customerDetailIntent.putExtra("room_desc", customerDesc.toString());
            customerDetailIntent.putExtra("room_price", customerPrice.toString());
            customerDetailIntent.putExtra("checkIn_date", checkInDate);
            customerDetailIntent.putExtra("checkOut_date", checkOutDate);
            startActivity(customerDetailIntent)
        }
    }
}