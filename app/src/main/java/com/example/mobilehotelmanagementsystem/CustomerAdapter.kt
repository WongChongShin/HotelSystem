package com.example.mobilehotelmanagementsystem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions


class CustomerAdapter(options: FirebaseRecyclerOptions<CustomerModel>) :
    FirebaseRecyclerAdapter<CustomerModel, CustomerAdapter.CustomerAdapterVH>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerAdapterVH {
        return CustomerAdapterVH(LayoutInflater.from(parent.context).inflate(R.layout.customer_row, parent, false))
    }

    override fun onBindViewHolder(holder: CustomerAdapterVH, position: Int, model: CustomerModel) {

        holder.roomNo.text = model.room_no
        holder.Name.text = model.name
        holder.Email.text = model.email
        holder.Phone.text = model.phone
        holder.CheckIn.text = model.checkIn
        holder.CheckOut.text = model.checkOut

    }

    class CustomerAdapterVH(itemView: View): RecyclerView.ViewHolder(itemView) {

        var roomNo:TextView = itemView.findViewById(R.id.customer_room_no_textView)
        var Name:TextView = itemView.findViewById(R.id.customer_name_textView)
        var Email:TextView = itemView.findViewById(R.id.customer_email_textView)
        var Phone:TextView = itemView.findViewById(R.id.customer_phone_textView)
        var CheckIn:TextView = itemView.findViewById(R.id.customer_checkIn_textView)
        var CheckOut:TextView = itemView.findViewById(R.id.customer_checkOut_textView)

    }

}