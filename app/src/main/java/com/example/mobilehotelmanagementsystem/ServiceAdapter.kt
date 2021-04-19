package com.example.mobilehotelmanagementsystem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.firebase.ui.database.FirebaseRecyclerAdapter


class ServiceAdapter (options: FirebaseRecyclerOptions<ServiceModel>)
    :FirebaseRecyclerAdapter<ServiceModel, ServiceAdapter.ServiceAdapterVH>(options){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceAdapter.ServiceAdapterVH {
        return ServiceAdapter.ServiceAdapterVH(
            LayoutInflater.from(parent.context).inflate(R.layout.customer_row, parent, false)
        )
    }


    override fun onBindViewHolder(holder: ServiceAdapter.ServiceAdapterVH, position: Int, model: ServiceModel) {

        holder.name.text = model.ServiceName
        holder.type.text = model.ServiceType
        holder.des.text = model.Description
        holder.price.text = model.Price.toString()
        holder.room.text = model.RoomDestination

    }

    class ServiceAdapterVH(itemView: View): RecyclerView.ViewHolder(itemView) {

        var name: TextView = itemView.findViewById(R.id.Service_view_name_text)
        var type: TextView = itemView.findViewById(R.id.Service_view_type_text)
        var des: TextView = itemView.findViewById(R.id.Service_view_des_text)
        var price: TextView = itemView.findViewById(R.id.Service_view_price_text)
        var room: TextView = itemView.findViewById(R.id.Service_view_room_text)


    }



}