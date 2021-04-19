package com.example.mobilehotelmanagementsystem

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import java.util.*

class Customer_purchase : AppCompatActivity() {

    private var customerUsername:String?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_purchase)

        customerUsername = intent?.getStringExtra("username").toString()
        val purchaseBackBtn: ImageView = findViewById<ImageView>(R.id.select_purchase_back);

        purchaseBackBtn.setOnClickListener{
            val backCustomerDetail = Intent(this, Customer_select_detail::class.java)
            startActivity(backCustomerDetail)
        }

        //--------------------room no--------------
        val customerRoomNo = intent?.getStringExtra("room_no")
        val roomNoOutput = findViewById<TextView>(R.id.room_no_output)

        roomNoOutput.setText(customerRoomNo)

        //--------------------description--------------
        val customerDesc = intent?.getStringExtra("room_desc")
        val roomDescOutput= findViewById<TextView>(R.id.room_desc_output)

        roomDescOutput.setText(customerDesc)


        //--------------------price--------------
        val customerPrice = intent?.getStringExtra("room_price")
        val roomPriceOutput = findViewById<TextView>(R.id.room_price_output)

        roomPriceOutput.setText(customerPrice)

        //--------------------check in date--------------
        val customerCheckIn = intent?.getStringExtra("checkIn_date")
        val roomCheckInOutput = findViewById<TextView>(R.id.room_check_in_output)

        roomCheckInOutput.setText(customerCheckIn)

        //--------------------check out date--------------
        val customerCheckOut = intent?.getStringExtra("checkOut_date")
        val roomCheckOutOutput = findViewById<TextView>(R.id.room_check_out_output)

        roomCheckOutOutput.setText(customerCheckOut)


        val purchaseButton = findViewById<Button>(R.id.select_detail_purchase)
        purchaseButton.setOnClickListener{

            val cardNum = findViewById<TextView>(R.id.customer_purchase_cardNumber)
            val cardHolderName = findViewById<TextView>(R.id.customer_purchase_holderName)
            val cardExpMonth= findViewById<TextView>(R.id.customer_purchase_expMonth)
            val cardExpYear= findViewById<TextView>(R.id.customer_purchase_expYear)
            val cardCvv= findViewById<TextView>(R.id.customer_purchase_cvv)



            validate(cardNum.text.toString(),cardHolderName.text.toString(), cardExpMonth.text.toString(), cardExpYear.text.toString(),cardCvv.text.toString())

        }



    }


    private fun validate(cardNum:String, cardHolderName:String, cardExpMonth:String, cardExpYear:String, cardCvv:String){
        val error_message = findViewById<TextView>(R.id.customer_purchase_error_message)
        val pruchaseRoom = Intent(this,Customer_pay_successful::class.java)
        val year:Int=Calendar.getInstance().get(Calendar.YEAR)%100

        val database = FirebaseDatabase.getInstance();
        val myRef = database.getReference("Room booking");
        val custRef = database.getReference("Customer Account");
        var payment_successful:Boolean=false
        val checkRadiobtn = findViewById<RadioGroup>(R.id.customer_purchase_radioGroup)
        val checkVisaCardbtn = findViewById<RadioButton>(R.id.customer_payment_visaBtn)
        val checkMasterCardbtn = findViewById<RadioButton>(R.id.customer_payment_masterBtn)

        if(cardNum=="" || cardHolderName==""||cardExpMonth==""||cardExpYear==""||cardCvv==""){

            error_message.setText("You are require to fill up all the blank ")
        }
        else {
            if (checkRadiobtn.getCheckedRadioButtonId() == -1) {
                error_message.setText("You are require to check the radio button ")
            }
            else {

                if (cardNum.length != 16) {
                    error_message.setText("Your card number need 16 word")
                } else {
                    if (cardExpMonth.toInt() < 1 || cardExpMonth.toInt() > 12) {
                        error_message.setText("Your month should be between 1-12")

                    } else {
                        if (cardExpYear.toInt() < year || cardExpYear.toInt() > year + 10) {
                            error_message.setText("Your year should not more than 10 year or lower than today lower")
                        } else {
                            if (cardCvv.length != 3) {
                                error_message.setText("Your CVV number should be 3 number")
                            } else {

                                val roomID: String = findViewById<TextView>(R.id.room_no_output).text.toString()
                                val roomDesc: String = findViewById<TextView>(R.id.room_desc_output).text.toString()
                                val roomCheckIn: String = findViewById<TextView>(R.id.room_check_in_output).text.toString()
                                val roomCheckOut: String = findViewById<TextView>(R.id.room_check_out_output).text.toString()
                                val roomPurchase: String = findViewById<TextView>(R.id.room_price_output).text.toString()
                                val roomActivate: String = "full"

                                val expiryDate: String = cardExpMonth + "/" + cardExpYear

                                val getCustomerAccountData = object : ValueEventListener {
                                    override fun onCancelled(error: DatabaseError) {

                                    }

                                    override fun onDataChange(snapshot: DataSnapshot) {

                                        for (s in snapshot.children) {
                                            val custName = s.child("CustomerName").getValue().toString()
                                            val cardNumberDatabase = s.child("CardNumber").getValue().toString()



                                            if (custName == customerUsername) {
                                                if(s.child("CardNumber").exists()){
                                                    if(cardNumberDatabase==" ") {
                                                        payment_successful = true
                                                        break
                                                    }
                                                    else if (cardNum == cardNumberDatabase) {
                                                        payment_successful = true

                                                        break
                                                    }
                                                }
                                                else{
                                                    custRef.child(customerUsername.toString()).child("CardNumber").setValue(" ")
                                                }

                                            }

                                        }
                                        if (payment_successful) {
                                            if (checkVisaCardbtn.isChecked()) {
                                                val visaCardText: String = findViewById<TextView>(R.id.customer_payment_visaText).text.toString()
                                                custRef.child(customerUsername.toString()).child("CardType").setValue(visaCardText)
                                            }
                                            if (checkMasterCardbtn.isChecked()) {
                                                val masterCardText: String = findViewById<TextView>(R.id.customer_payment_MasterText).text.toString()
                                                custRef.child(customerUsername.toString()).child("CardType").setValue(masterCardText)
                                            }

                                            custRef.child(customerUsername.toString()).child("RoomNo").setValue(roomID)

                                            custRef.child(customerUsername.toString()).child("CardNumber").setValue(cardNum)
                                            custRef.child(customerUsername.toString()).child("CardHolderName").setValue(cardHolderName)
                                            custRef.child(customerUsername.toString()).child("CardExpiryDate").setValue(expiryDate)
                                            custRef.child(customerUsername.toString()).child("CardCvv").setValue(cardCvv)

                                            myRef.child(roomID).child("RoomNo").setValue(roomID)
                                            myRef.child(roomID).child("Description").setValue(roomDesc)
                                            myRef.child(roomID).child("CheckIn").setValue(roomCheckIn)
                                            myRef.child(roomID).child("CheckOut").setValue(roomCheckOut)
                                            myRef.child(roomID).child("Purchase").setValue(roomPurchase)
                                            myRef.child(roomID).child("Status").setValue(roomActivate)
                                            myRef.child(roomID).child("CustomerName").setValue(customerUsername)



                                            startActivity(pruchaseRoom)
                                        }
                                        else{
                                            error_message.setText("Your bank account cannot be find in database")
                                        }
                                    }

                                }
                                val userNameQuery: Query = custRef.orderByChild("CustomerName").equalTo(customerUsername)

                                userNameQuery.addValueEventListener(getCustomerAccountData)
                                userNameQuery.addListenerForSingleValueEvent(getCustomerAccountData)

                            }
                        }


                    }
                }
            }
        }
    }
}







