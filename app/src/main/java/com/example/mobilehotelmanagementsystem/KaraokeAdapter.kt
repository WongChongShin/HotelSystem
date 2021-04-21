package com.example.mobilehotelmanagementsystem

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class KaraokeAdapter (val context: Context, val karaokeList: ArrayList<KaraokeModel>, private val listener: OnItemClickListener):
        RecyclerView.Adapter<KaraokeAdapter.Holder>() {

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(karaokeList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KaraokeAdapter.Holder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.karaoke_row, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return karaokeList.size
    }

    inner class Holder(view: View?) : RecyclerView.ViewHolder(view!!), View.OnClickListener {
        val room_no = view?.findViewById<TextView>(R.id.karaoke_room_no_textView)
        val phone = view?.findViewById<TextView>(R.id.karaoke_phone_textView)
        val time = view?.findViewById<TextView>(R.id.karaoke_time_textView)
        val karaokeEdit = view?.findViewById<ImageView>(R.id.edit_row_karaoke)
        val karaokeDelete = view?.findViewById<ImageView>(R.id.delete_row_karaoke)

        init {
            karaokeEdit?.setOnClickListener(this)
            karaokeDelete?.setOnClickListener(this)
        }

        fun bind(karaoke: KaraokeModel) {
            room_no?.text = karaoke.room_no
            phone?.text = karaoke.phone
            time?.text = karaoke.time

            karaokeEdit?.isEnabled = true
            karaokeEdit?.isClickable = true

            karaokeDelete?.isEnabled = true
            karaokeDelete?.isClickable = true
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}