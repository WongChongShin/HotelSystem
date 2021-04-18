package com.example.mobilehotelmanagementsystem

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

class customer_login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_login)



        val customerLoginBtn = findViewById<Button>(R.id.customer_signIn)

        customerLoginBtn.setOnClickListener{

            val customerUsername = findViewById<TextView>(R.id.customer_sign_in_username).text
            val customerPassword= findViewById<TextView>(R.id.customer_sign_in_password).text
            validate(customerUsername.toString(),customerPassword.toString());

        }


        val customerRegisterBtn = findViewById<Button>(R.id.customer_register_button)

        customerRegisterBtn.setOnClickListener{

            val customerRegisterIntent = Intent(this, customer_register::class.java)
            startActivity(customerRegisterIntent)

        }

    }
    private fun validate(userName:String, password:String){
        val database = FirebaseDatabase.getInstance()
        val roomDatabaseRef = database.getReference("Customer Account");
        val customerSignInIntent = Intent(this, CustomerMainActivity::class.java)
        val error_message = findViewById<TextView>(R.id.customer_error_message)




        if(userName=="" || password==""){

            error_message.setText("You are require to fill up all the blank ")
        }
        else{

            val getCustomerAccountData = object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {

                }

                override fun onDataChange(snapshot: DataSnapshot) {

                    for (s in snapshot.children) {
                        val custPassword = s.child("Password").getValue().toString()
                        val custEmail = s.child("EmailAddress").getValue().toString()
                        val custName=s.child("CustomerName").getValue().toString()

                        if (userName==custEmail && password==custPassword) {
                            customerSignInIntent.putExtra("username", custName.toString())

                            val toast = Toast.makeText(applicationContext, "Login Successful", Toast.LENGTH_SHORT)
                            toast.show()
                            GlobalScope.async {
                                delay(800)
                                startActivity(customerSignInIntent)
                            }


                        }
                        else{
                            error_message.setText("You email and password cannot be found")
                        }
                    }

                }


            }


            val emailAddressQuery: Query = roomDatabaseRef.orderByChild("CustomerName")

            emailAddressQuery.addValueEventListener(getCustomerAccountData)
            emailAddressQuery.addListenerForSingleValueEvent(getCustomerAccountData)


        }
    }
}