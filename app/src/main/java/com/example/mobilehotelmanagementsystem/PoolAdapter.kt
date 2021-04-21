package com.example.mobilehotelmanagementsystem

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PoolAdapter(val context: Context, val poolList: ArrayList<PoolModel>, private val listener: OnItemClickListener):
RecyclerView.Adapter<PoolAdapter.Holder>() {

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(poolList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoolAdapter.Holder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.pool_row, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return poolList.size
    }

    inner class Holder(view: View?) : RecyclerView.ViewHolder(view!!), View.OnClickListener {
        val room_no = view?.findViewById<TextView>(R.id.pool_room_no_textView)
        val phone = view?.findViewById<TextView>(R.id.pool_phone_textView)
        val time = view?.findViewById<TextView>(R.id.pool_time_textView)
        val poolEdit = view?.findViewById<ImageView>(R.id.edit_row_pool)
        val poolDelete = view?.findViewById<ImageView>(R.id.delete_row_pool)

        init{
            poolEdit?.setOnClickListener(this)
            poolDelete?.setOnClickListener(this)
        }

        fun bind(pool: PoolModel) {
            room_no?.text = pool.room_no
            phone?.text = pool.phone
            time?.text = pool.time

            poolEdit?.isEnabled = true
            poolEdit?.isClickable = true

            poolDelete?.isEnabled = true
            poolDelete?.isClickable = true
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
