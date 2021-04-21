package com.example.mobilehotelmanagementsystem

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MassageAdapter (val context: Context, val massageList: ArrayList<MassageModel>, private val listener: OnItemClickListener):
        RecyclerView.Adapter<MassageAdapter.Holder>() {

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(massageList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MassageAdapter.Holder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.massage_row, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return massageList.size
    }

    inner class Holder(view: View?) : RecyclerView.ViewHolder(view!!), View.OnClickListener {
        val room_no = view?.findViewById<TextView>(R.id.massage_room_no_textView)
        val phone = view?.findViewById<TextView>(R.id.massage_phone_textView)
        val time = view?.findViewById<TextView>(R.id.massage_time_textView)
        val massageEdit = view?.findViewById<ImageView>(R.id.edit_row_massage)
        val massageDelete = view?.findViewById<ImageView>(R.id.delete_row_massage)

        init{
            massageEdit?.setOnClickListener(this)
            massageDelete?.setOnClickListener(this)
        }

        fun bind(massage: MassageModel) {
            room_no?.text = massage.room_no
            phone?.text = massage.phone
            time?.text = massage.time

            massageEdit?.isEnabled = true
            massageEdit?.isClickable = true

            massageDelete?.isEnabled = true
            massageDelete?.isClickable = true
        }

        override fun onClick(v: View?) {
            val position=adapterPosition
            if(position!= RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

}
