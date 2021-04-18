package com.example.mobilehotelmanagementsystem

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class Customer_food_service_order : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_food_service_order)

        //get order object that is chossen
        val order : FoodService =  intent?.getSerializableExtra("order") as FoodService


        //initialize UI
        var orderName : TextView = findViewById(R.id.food_order_name_textview)
        orderName.text = order.name

        var orderPrice : TextView = findViewById(R.id.food_order_price_textview)
        orderPrice.text = "RM ${order.price}"

        var orderTime : TextView = findViewById(R.id.food_order_time_textview)
        var displayTimeText  = ""
        if (order.minTime24 < 12) {
            displayTimeText += "${order.minTime24}am ~ "
        }else if (order.minTime24 == 12){
            displayTimeText += "${12}pm ~ "
        }else if (order.minTime24 > 12){
            displayTimeText += "${order.minTime24 - 12}pm ~ "
        }
        if (order.maxTime24 < 12) {
            displayTimeText += "${order.maxTime24}am"
        }else if (order.maxTime24 == 12){
            displayTimeText += "${order.maxTime24}pm"
        }else if (order.maxTime24 > 12){
            displayTimeText += "${order.maxTime24}pm"
        }
        orderTime.text = displayTimeText

        var orderDes : TextView = findViewById(R.id.food_order_des_textview)
        orderDes.text = order.description


        //need to add the function to store the data in fire base later
        var orderButton : Button = findViewById(R.id.food_order_btn)
        orderButton.setOnClickListener(){
            val toast = Toast.makeText(applicationContext, "Your order has been created", Toast.LENGTH_SHORT)
            toast.show()

            val services = Intent(this, Customer_select_service::class.java)
            startActivity(services)
        }


    }


}