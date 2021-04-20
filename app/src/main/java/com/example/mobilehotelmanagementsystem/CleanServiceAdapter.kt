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

class CleanServiceAdapter(val fragment: cleaning_list, val cleanServiceList: ArrayList<CleanServiceModel>, private val listener: OnItemClickListener):
        RecyclerView.Adapter<CleanServiceAdapter.Holder>() {


    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(cleanServiceList[position])


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(fragment.context)
                .inflate(R.layout.row_clean_service, parent, false)

        return Holder(view)
    }

    override fun getItemCount(): Int {
        return cleanServiceList.size
    }


    inner class Holder(view: View?) : RecyclerView.ViewHolder(view!!),View.OnClickListener {
        val cleanRoom = view?.findViewById<TextView>(R.id.row_clean_room)
        val cleanName = view?.findViewById<TextView>(R.id.row_clean_name)
        val cleanPhone = view?.findViewById<TextView>(R.id.row_clean_phone)
        val cleanEdit = view?.findViewById<ImageButton>(R.id.clean_edit_button)




        init{
            cleanEdit?.setOnClickListener(this)
        }

        fun bind(clean: CleanServiceModel) {
            cleanRoom?.text = clean.userRoom
            cleanName?.text = clean.userName
            cleanPhone?.text = clean.userPhone

            cleanEdit?.isEnabled = true
            cleanEdit?.isClickable = true


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