package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class activity_customer_select_cleaning_service : AppCompatActivity() {
    var  serviceArr = Array(3, { CustomerService() })

    var currentUsername = ""
    var currentEmail = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_select_cleaning_service)

        //get username and email
        currentUsername  =  intent?.getStringExtra("username") as String
        currentEmail  =  intent?.getStringExtra("email") as String

        //create a array of all available orders
        //(hard coded)
        initServices()

        //initialize buttons corresponding to each orders
        var orderButton1: Button = findViewById(R.id.select_clean_btn_1)
        var orderButton2: Button = findViewById(R.id.select_clean_btn_2)
        var orderButton3: Button = findViewById(R.id.select_clean_btn_3)


        orderButton1.setOnClickListener(){selectService(0)}
        orderButton2.setOnClickListener(){selectService(1)}
        orderButton3.setOnClickListener(){selectService(2)}




    }




    fun selectService( itemIndex: Int){
        if (!serviceArr[itemIndex].availability){
            val toast = Toast.makeText(applicationContext, "This service is not available right now", Toast.LENGTH_SHORT)
            toast.show()
            return
        }

        if (serviceArr[itemIndex].checkTimeAvailable()){
            val foodOrder= Intent(this, Customer_food_service_order::class.java)
            foodOrder.putExtra("order", serviceArr[itemIndex])
            foodOrder.putExtra("username", currentUsername)
            foodOrder.putExtra("email", currentEmail)
            startActivity(foodOrder)

        }else {
            val toast = Toast.makeText(applicationContext, "This service is not available right now", Toast.LENGTH_SHORT)
            toast.show()
        }

    }

    fun initServices(){
        serviceArr[0].name = "Quick Cleaning"
        serviceArr[0].price = 10.00f
        serviceArr[0].description = "A quick room clean up that can be done within 30 minutes"
        serviceArr[0].minTime24 = 10
        serviceArr[0].maxTime24 = 17
        serviceArr[0].picture = R.drawable.american_breakfast
        serviceArr[0].serviceType = "Cleaning Service"

        serviceArr[1].name = "Advanced Cleaning"
        serviceArr[1].price = 20.00f
        serviceArr[1].description = "Room clean up with changing bed sheets"
        serviceArr[1].minTime24 = 10
        serviceArr[1].maxTime24 = 17
        serviceArr[1].picture = R.drawable.salad
        serviceArr[1].serviceType = "Cleaning Service"

        serviceArr[2].name = "Deep Cleaning"
        serviceArr[2].price = 35.00f
        serviceArr[2].description = "The most luxurious clean up in the house"
        serviceArr[2].minTime24 = 10
        serviceArr[2].maxTime24 = 17
        serviceArr[2].picture = R.drawable.tea
        serviceArr[2].serviceType = "Cleaning Service"


    }
}