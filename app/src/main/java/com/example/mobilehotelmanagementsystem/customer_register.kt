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

class customer_register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_register)


        val customerRegisterBtn = findViewById<Button>(R.id.customer_registerBtn)

        customerRegisterBtn.setOnClickListener{

            val customerUsername = findViewById<TextView>(R.id.customer_register_username).text
            val customerEmail = findViewById<TextView>(R.id.customer_register_mail).text
            val customerPhone= findViewById<TextView>(R.id.customer_register_phone).text
            val customerPassword= findViewById<TextView>(R.id.customer_register_password).text
            validate(customerUsername.toString(),customerEmail.toString(),customerPhone.toString(),customerPassword.toString());

        }


    }
    private fun validate(userName:String,email:String, phone:String, password:String){
        val emailPattern="[a-zA-Z0-9._-]+@gmail+\\.+com+"
        val error_message = findViewById<TextView>(R.id.customer_register_error_message)
        val customerLogInIntent = Intent(this, customer_login::class.java)

        val database = FirebaseDatabase.getInstance();
        val myRef = database.getReference("Customer Account");
        var login_successful:Boolean=false
        if(userName=="" || password==""||email==""||phone==""){

            error_message.setText("You are require to fill up all the blank ")
        }
        else{
            if(password.length<8){
                error_message.setText("Your password need more than 8 word")
            }
            else {
                if (phone.length < 10 || phone.length > 11) {
                    error_message.setText("Your phone number should be 10-11 number")

                } else {
                    if(email.matches(emailPattern.toRegex())){
                        val getCustomerAccountData = object : ValueEventListener {
                            override fun onCancelled(error: DatabaseError) {

                            }

                            override fun onDataChange(snapshot: DataSnapshot) {

                                for (s in snapshot.children) {
                                    val custPassword = s.child("Password").getValue().toString()
                                    val custEmail = s.child("EmailAddress").getValue().toString()
                                    val custName=s.child("CustomerName").getValue().toString()
                                    val custPhone:String=s.child("PhoneNumber").getValue().toString()

                                    if (userName==custName || password==custPassword||email==custEmail || phone==custPhone) {
                                        login_successful=false
                                        break

                                    }
                                    else{
                                        login_successful=true
                                    }
                                }
                                if(login_successful) {

                                    myRef.child(userName).child("CustomerName").setValue(userName)
                                    myRef.child(userName).child("PhoneNumber").setValue(phone)
                                    myRef.child(userName).child("EmailAddress").setValue(email)
                                    myRef.child(userName).child("Password").setValue(password)

                                    val toast = Toast.makeText(applicationContext, "Register Successful", Toast.LENGTH_SHORT)
                                    toast.show()
                                    GlobalScope.async {
                                        delay(800)
                                        startActivity(customerLogInIntent)
                                        findViewById<TextView>(R.id.customer_register_username).setText("")
                                        findViewById<TextView>(R.id.customer_register_mail).setText("")
                                        findViewById<TextView>(R.id.customer_register_phone).setText("")
                                        findViewById<TextView>(R.id.customer_register_password).setText("")
                                    }


                                }
                                else {
                                    error_message.setText("Your information is already stored in database before")
                                }

                            }


                        }

                        val emailAddressQuery: Query = myRef.orderByChild("CustomerName")

                        //emailAddressQuery.addValueEventListener(getCustomerAccountData)
                        emailAddressQuery.addListenerForSingleValueEvent(getCustomerAccountData)


                    }
                    else{
                        error_message.setText("Your email address should be put @gmail.com a back")
                    }



                }
            }
        }
    }
}