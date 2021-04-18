package com.example.mobilehotelmanagementsystem

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Customer_room_adapter(val context: Context, val roomList: ArrayList<customer_room_list>,private val listener:OnItemClickListener):RecyclerView.Adapter<Customer_room_adapter.Holder>() {

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(roomList[position])


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.activity_customer_room_list, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return roomList.size
    }


    inner class Holder(view: View?) : RecyclerView.ViewHolder(view!!),View.OnClickListener {
        val roomNo = view?.findViewById<TextView>(R.id.customer_room_no)
        val roomDesc = view?.findViewById<TextView>(R.id.customer_room_description)
        val roomPrice = view?.findViewById<TextView>(R.id.customer_price)
        val button=view?.findViewById<Button>(R.id.customer_book_room_btn)
        val roomStatus=String

        init{
            button?.setOnClickListener(this)
        }

        fun bind(room: customer_room_list) {
            roomNo?.text = room.roomNo
            roomDesc?.text = room.roomDesc
            roomPrice?.text = room.roomPrice

            if(room.roomStatus=="full") {
                button?.setBackgroundColor(Color.RED);
                button?.setText("Full")
                button?.setTextColor(Color.BLACK);
                button?.isEnabled = false
                button?.isClickable = false
            }
            else{
                button?.setBackgroundColor(Color.parseColor("#ff99cc00"));
                button?.setText("Book");
                button?.setTextColor(Color.BLACK);
                button?.isEnabled = true
                button?.isClickable = true
            }



        }

        override fun onClick(v: View?) {
            val position=adapterPosition
            if(position!=RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

}

