package com.example.mobilehotelmanagementsystem

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*


class checkIn_and_checkOut : AppCompatActivity(),CustomerAdapter.OnItemClickListener {

    private val customerRoomNoArr = ArrayList<String>()
    private val customerNameArr = ArrayList<String>()
    private val customerEmailArr = ArrayList<String>()
    private val customerPhoneArr = ArrayList<String>()
    private val customerCheckInArr = ArrayList<String>()
    private val customerCheckOutArr = ArrayList<String>()
    private val bd: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference: DatabaseReference = bd.getReference("Customer List")
    private lateinit var customerList: ArrayList<CustomerModel>
    private lateinit var recyclerView: RecyclerView
    var customer_search_input: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_in_and_check_out)

        //Recycler View stuff
        recyclerView = findViewById(R.id.customer_list_view)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        customerList = arrayListOf<CustomerModel>()

        val deleteCustomerListBtn = findViewById<ImageView>(R.id.delete_row_customer)

        val btnAddCustomer = findViewById<ImageView>(R.id.add_check_logo)

        btnAddCustomer.setOnClickListener() {

            val addNewCustomerIntent = Intent(this, AddNewCustomer::class.java)
            startActivity(addNewCustomerIntent)
        }

        val checkBackBtn: ImageView = findViewById<ImageView>(R.id.staff_page_back_logo);

        checkBackBtn.setOnClickListener {
            val backCustomerMain = Intent(this, main_page::class.java)

            startActivity(backCustomerMain)
        }


        //search data
        customer_search_input = findViewById<EditText>(R.id.staff_editSearch)
        customer_search_input!!.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(word: CharSequence, start: Int,
                                       before: Int, count: Int) {

                val customer_search_input: String = word.toString()

                val customerSearchRoom: Query = FirebaseDatabase.getInstance().reference.child("Customer List")
                        .orderByChild("Room_No").startAt(customer_search_input).endAt(customer_search_input + "\ufbff")

                customerSearchRoom.addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(error: DatabaseError) {

                    }

                    override fun onDataChange(snapshot: DataSnapshot) {

                        customerList.clear()
                        for (s in snapshot.children) {

                            val room_no = s.child("Room_No").getValue().toString()
                            val name = s.child("Name").getValue().toString()
                            val email = s.child("Email").getValue().toString()
                            val phone = s.child("Phone").getValue().toString()
                            val checkIn = s.child("Check In").getValue().toString()
                            val checkOut = s.child("Check Out").getValue().toString()
                            val cus = s.getValue(CustomerModel::class.java)

                            cus!!.room_no = room_no
                            customerRoomNoArr.add(room_no)
                            cus!!.name = name
                            customerNameArr.add(name)
                            cus!!.email = email
                            customerEmailArr.add(email)
                            cus!!.phone = phone
                            customerPhoneArr.add(phone)
                            cus!!.checkIn = checkIn
                            customerCheckInArr.add(checkIn)
                            cus!!.checkOut = checkOut
                            customerCheckOutArr.add(checkOut)
                            customerList.add(cus)

                        }
                        val adapter = CustomerAdapter(this@checkIn_and_checkOut, customerList, this@checkIn_and_checkOut)

                        recyclerView.setAdapter(adapter)
                    }

                })
            }
        })

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(snapshot: DataSnapshot) {

                customerList.clear()
                if (customer_search_input!!.text.toString() == "") {
                    for (s in snapshot.children) {

                        val room_no = s.child("Room_No").getValue().toString()
                        val name = s.child("Name").getValue().toString()
                        val email = s.child("Email").getValue().toString()
                        val phone = s.child("Phone").getValue().toString()
                        val checkIn = s.child("Check In").getValue().toString()
                        val checkOut = s.child("Check Out").getValue().toString()
                        val cus = s.getValue(CustomerModel::class.java)

                        cus!!.room_no = room_no
                        customerRoomNoArr.add(room_no)
                        cus!!.name = name
                        customerNameArr.add(name)
                        cus!!.email = email
                        customerEmailArr.add(email)
                        cus!!.phone = phone
                        customerPhoneArr.add(phone)
                        cus!!.checkIn = checkIn
                        customerCheckInArr.add(checkIn)
                        cus!!.checkOut = checkOut
                        customerCheckOutArr.add(checkOut)
                        customerList.add(cus)

                    }
                    val adapter = CustomerAdapter(this@checkIn_and_checkOut, customerList, this@checkIn_and_checkOut)

                    recyclerView.setAdapter(adapter)
                }
            }
        })
    }

    override fun onItemClick(position: Int) {
        val deleteCustomerListBtn = findViewById<ImageView>(R.id.delete_row_customer)
        deleteCustomerListBtn.setOnClickListener {

            databaseReference.child("Name").removeValue()

        }

        val editCustomerListBtn = findViewById<ImageView>(R.id.edit_row_customer)
        editCustomerListBtn.setOnClickListener {

            val customerIntent = Intent(this, EditCustomerList::class.java)

            customerIntent.putExtra("Room_No", customerRoomNoArr[position].toString());
            customerIntent.putExtra("Name", customerNameArr[position].toString());
            customerIntent.putExtra("Email", customerEmailArr[position].toString());
            customerIntent.putExtra("Phone", customerPhoneArr[position].toString());
            customerIntent.putExtra("Check In", customerCheckInArr[position].toString());
            customerIntent.putExtra("Check Out", customerCheckOutArr[position].toString());

            startActivity(customerIntent)


        }
    }
}
