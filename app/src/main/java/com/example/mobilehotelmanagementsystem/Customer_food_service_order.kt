package com.example.mobilehotelmanagementsystem

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import java.util.*


class Customer_food_service_order : AppCompatActivity() {

    var currentUsername = ""
    var currentEmail = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_food_service_order)

        //get order object that is chossen
        val order : FoodService =  intent?.getSerializableExtra("order") as FoodService

        //get username and email
        currentUsername  =  intent?.getStringExtra("username") as String
        currentEmail  =  intent?.getStringExtra("email") as String

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

        var orderImage : ImageView = findViewById(R.id.food_order_image)
        orderImage.setImageResource(order.picture)


        //need to add the function to store the data in fire base later
        var orderButton : Button = findViewById(R.id.food_order_btn)
        orderButton.setOnClickListener(){
            validateRoom(order);
        }



    }


    fun validateRoom (order: FoodService){
       //get the room booking database
       val database = FirebaseDatabase.getInstance()
       val roomDatabaseRef = database.getReference("Room booking");
        val services = Intent(this, Customer_select_service::class.java)

       //create event listener
       val getRoomData = object : ValueEventListener {
           override fun onCancelled(error: DatabaseError) {

           }

           //check every room here
           override fun onDataChange(snapshot: DataSnapshot) {

               val customerRoom = findViewById<TextView>(R.id.food_order_room_edit).text.toString()

               for (s in snapshot.children) {
                   val roomNumber  = s.child("RoomNo").getValue().toString()
                   if (customerRoom == roomNumber){
                       //found room
                       val toast = Toast.makeText(applicationContext, "Your order has been created", Toast.LENGTH_SHORT)
                       toast.show()

                       //add stuff
                       addOrderDatabase(order,customerRoom )

                       //move back to service page
                       services.putExtra("username", currentUsername)
                       services.putExtra("email", currentEmail)
                       startActivity(services)
                       return

                   }

               }

               //room not found
               val toast = Toast.makeText(applicationContext, "This room no is not valid, try 100 - 107", Toast.LENGTH_SHORT)
               toast.show()

           }


       }

       //add event listener
       roomDatabaseRef.addValueEventListener(getRoomData)
   }

    //adding a new service entry to service database
    fun addOrderDatabase(order: FoodService, room:String){
        val database = FirebaseDatabase.getInstance()
        val serviceDatabase = database.getReference("Customer Service")

        //create event listener
        val setServiceData = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            //check every room here
            override fun onDataChange(snapshot: DataSnapshot) {

                //generate key based on current time
                var currentTime = Calendar.getInstance()
                var key = currentTime.get(Calendar.SECOND ). toString()
                key += currentTime.get(Calendar.MINUTE).toString()
                key += currentTime.get(Calendar.HOUR_OF_DAY).toString()
                key += currentTime.get(Calendar.DAY_OF_YEAR).toString()
                key += currentTime.get(Calendar.YEAR).toString()

                //add entry
                serviceDatabase.child(key).child("ServiceName").setValue(order.name)
                serviceDatabase.child(key).child("ServiceType").setValue("Food Service")
                serviceDatabase.child(key).child("Description").setValue(order.description)
                serviceDatabase.child(key).child("Price").setValue(order.price)
                serviceDatabase.child(key).child("RoomDestination").setValue(room)
                serviceDatabase.child(key).child("CustomerEmail").setValue(currentEmail)


            }


        }

        val serviceQuery: Query = serviceDatabase.orderByChild("ServiceName")
        serviceQuery.addListenerForSingleValueEvent(setServiceData)

    }
}