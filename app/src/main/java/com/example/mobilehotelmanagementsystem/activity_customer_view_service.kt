package com.example.mobilehotelmanagementsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class activity_customer_view_service : AppCompatActivity() {

    private val bd: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val serviceDatabase = bd.getReference("Customer Service")
    var serviceAdapter: ServiceAdapter? = null
    private val recyclerView : RecyclerView = findViewById(R.id.service_view_recycler)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_view_service)


        setUpRecyclerView()

    }

    private fun setUpRecyclerView(){

        val query: DatabaseReference = serviceDatabase
        val firebaseRecyclerOptions: FirebaseRecyclerOptions<ServiceModel> = FirebaseRecyclerOptions.Builder<ServiceModel>()
            .setQuery(query, ServiceModel::class.java).build()

        serviceAdapter = ServiceAdapter(firebaseRecyclerOptions)

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = serviceAdapter
    }

    override fun onStart() {
        super.onStart()
        serviceAdapter!!.startListening()
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceAdapter!!.stopListening()
    }
}