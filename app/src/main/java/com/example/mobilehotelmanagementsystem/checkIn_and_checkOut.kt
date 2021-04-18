package com.example.mobilehotelmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class checkIn_and_checkOut : AppCompatActivity() {

    private val bd:FirebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference: DatabaseReference = bd.getReference("Customer List")
    var customerAdapter: CustomerAdapter? = null
    private val recyclerView : RecyclerView = findViewById(R.id.customer_list_view)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_in_and_check_out)

        setUpRecyclerView()

        val btnCheckAdd: ImageView = findViewById<ImageView>(R.id.add_check_logo);
        val btnCheckBack: ImageView = findViewById<ImageView>(R.id.staff_page_back_logo);

        btnCheckAdd.setOnClickListener{
            val checkAddIntent = Intent(this, AddNewCustomer::class.java)
            startActivity(checkAddIntent)
        }

        btnCheckBack.setOnClickListener{
                val checkBackIntent = Intent(this, main_page::class.java)
                startActivity(checkBackIntent)
        }
    }

    private fun setUpRecyclerView(){

        val query: DatabaseReference = databaseReference
        val firebaseRecyclerOptions: FirebaseRecyclerOptions<CustomerModel> = FirebaseRecyclerOptions.Builder<CustomerModel>()
            .setQuery(query, CustomerModel::class.java).build()

        customerAdapter = CustomerAdapter(firebaseRecyclerOptions)

        recyclerView.layoutManager =LinearLayoutManager(this)

        recyclerView.adapter = customerAdapter
    }

   override fun onStart() {
        super.onStart()
        customerAdapter!!.startListening()
    }

    override fun onDestroy() {
        super.onDestroy()
        customerAdapter!!.stopListening()
    }
}