package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast


class Customer_select_food_service : AppCompatActivity() {

    var  serviceArr = Array(6, { CustomerService() })

    var currentUsername = ""
    var currentEmail = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_select_food_service)

        //get username and email
        currentUsername  =  intent?.getStringExtra("username") as String
        currentEmail  =  intent?.getStringExtra("email") as String

        //create a array of all available orders
        //(hard coded)
        initServices()

        //initialize buttons corresponding to each orders
        var orderButton1: Button = findViewById(R.id.select_food_btn_1)
        var orderButton2: Button = findViewById(R.id.select_food_btn_2)
        var orderButton3: Button = findViewById(R.id.select_food_btn_3)
        var orderButton4: Button = findViewById(R.id.select_food_btn_4)
        var orderButton5: Button = findViewById(R.id.select_food_btn_5)
        var orderButton6: Button = findViewById(R.id.select_food_btn_6)

        orderButton1.setOnClickListener(){selectService(0)}
        orderButton2.setOnClickListener(){selectService(1)}
        orderButton3.setOnClickListener(){selectService(2)}
        orderButton4.setOnClickListener(){selectService(3)}
        orderButton5.setOnClickListener(){selectService(4)}
        orderButton6.setOnClickListener(){selectService(5)}



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
        serviceArr[0].name = "American Breakfast"
        serviceArr[0].price = 10.00f
        serviceArr[0].description = "Eggs, sausagus and toast"
        serviceArr[0].minTime24 = 7
        serviceArr[0].maxTime24 = 11
        serviceArr[0].picture = R.drawable.american_breakfast

        serviceArr[1].name = "Farmers Salad"
        serviceArr[1].price = 8.00f
        serviceArr[1].description = "An assortment of fresh veggies"
        serviceArr[1].minTime24 = 10
        serviceArr[1].maxTime24 = 20
        serviceArr[1].picture = R.drawable.salad

        serviceArr[2].name = "Specialty tea"
        serviceArr[2].price = 5.00f
        serviceArr[2].description = "Home brewed chinese herbal tea"
        serviceArr[2].minTime24 = 13
        serviceArr[2].maxTime24 = 20
        serviceArr[2].picture = R.drawable.tea

        serviceArr[3].name = "Kids meal"
        serviceArr[3].price = 18.00f
        serviceArr[3].description = "Sandwich, Milo and a cookie for ages under 6"
        serviceArr[3].minTime24 = 12
        serviceArr[3].maxTime24 = 20
        serviceArr[3].picture = R.drawable.kids_meal

        serviceArr[4].name = "Nyonya Rice Special"
        serviceArr[4].price = 15.00f
        serviceArr[4].description = "Nyonya rice with ayam rendang"
        serviceArr[4].minTime24 = 12
        serviceArr[4].maxTime24 = 20
        serviceArr[4].picture = R.drawable.nyonya_rice

        serviceArr[5].name = "Smoothie Deluxe"
        serviceArr[5].price = 12.00f
        serviceArr[5].description = "Smoothie of mixed fruits"
        serviceArr[5].minTime24 = 15
        serviceArr[5].maxTime24 = 20
        serviceArr[5].picture = R.drawable.smoothie
        serviceArr[5].availability = false



    }

}

