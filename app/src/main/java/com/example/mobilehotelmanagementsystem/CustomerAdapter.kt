package com.example.mobilehotelmanagementsystem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.*


class CustomerAdapter(options: FirebaseRecyclerOptions<CustomerModel>) :
    FirebaseRecyclerAdapter<CustomerModel, CustomerAdapter.CustomerAdapterVH>(options) {

    private val bd: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference: DatabaseReference = bd.getReference("Customer List")

    val query: Query = databaseReference

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerAdapterVH {
        return CustomerAdapterVH(LayoutInflater.from(parent.context).inflate(R.layout.customer_row, parent, false))
    }

    override fun onBindViewHolder(holder: CustomerAdapterVH, position: Int, model: CustomerModel) {

        var getData = object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {

                for(s in snapshot.children){
                    holder.room_no.text = s.child("Room_No").getValue().toString()
                    holder.name.text = s.child("Name").getValue().toString()
                    holder.email.text = s.child("Email").getValue().toString()
                    holder.phone.text = s.child("Phone").getValue().toString()
                    holder.checkIn.text = s.child("Check In").getValue().toString()
                    holder.checkOut.text = s.child("Check Out").getValue().toString()
                }
            }
        }
        query.addValueEventListener(getData)
        query.addListenerForSingleValueEvent(getData)
    }

    class CustomerAdapterVH(itemView: View): RecyclerView.ViewHolder(itemView) {

        val room_no:TextView = itemView.findViewById(R.id.customer_room_no_textView)
        val name:TextView = itemView.findViewById(R.id.customer_name_textView)
        val email:TextView = itemView.findViewById(R.id.customer_email_textView)
        val phone:TextView = itemView.findViewById(R.id.customer_phone_textView)
        val checkIn:TextView = itemView.findViewById(R.id.customer_checkIn_textView)
        val checkOut:TextView = itemView.findViewById(R.id.customer_checkOut_textView)

    }

}