package com.example.mobilehotelmanagementsystem

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class FoodServiceAdapter(val fragment: food_list, val foodServiceList: ArrayList<FoodServiceModel>, private val listener: OnItemClickListener):
        RecyclerView.Adapter<FoodServiceAdapter.Holder>() {


    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(foodServiceList[position])


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(fragment.context)
                .inflate(R.layout.row_food_service, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return foodServiceList.size
    }


    inner class Holder(view: View?) : RecyclerView.ViewHolder(view!!),View.OnClickListener {
        val foodRoom = view?.findViewById<TextView>(R.id.row_food_room)
        val foodName = view?.findViewById<TextView>(R.id.row_food_name)
        val foodPhone = view?.findViewById<TextView>(R.id.row_food_phone)
        val foodEdit = view?.findViewById<ImageButton>(R.id.food_edit_button)

        init{
            foodEdit?.setOnClickListener(this)
        }

        fun bind(food: FoodServiceModel) {
            foodRoom?.text = food.userfoodRoom
            foodName?.text = food.userfoodName
            foodPhone?.text = food.userfoodPhone

            foodEdit?.isEnabled = true
            foodEdit?.isClickable = true

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