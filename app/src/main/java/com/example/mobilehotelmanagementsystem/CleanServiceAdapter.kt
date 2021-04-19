package com.example.mobilehotelmanagementsystem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class CleanServiceAdapter(options: FirebaseRecyclerOptions<CleanServiceModel>) :
    FirebaseRecyclerAdapter<CleanServiceModel, CleanServiceAdapter.CleanServiceAdapterVH>(options) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CleanServiceAdapterVH {
        return CleanServiceAdapterVH(LayoutInflater.from(parent.context).inflate(R.layout.row_clean_service, parent, false))
    }

    override fun onBindViewHolder(holder: CleanServiceAdapterVH, position: Int, model: CleanServiceModel) {

        holder.userRoom.text = model.userRoom
        holder.userName.text = model.userName
        holder.userPhone.text = model.userPhone
    }

    class CleanServiceAdapterVH(itemView: View) : RecyclerView.ViewHolder(itemView){
        val userRoom:TextView = itemView.findViewById(R.id.row_clean_room_tv)
        val userName:TextView = itemView.findViewById(R.id.row_clean_name_tv)
        val userPhone:TextView = itemView.findViewById(R.id.row_clean_phone_tv)

        /*var getCleanData = object:ValueEventListener{
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                var sbRoom = StringBuilder()

                for(s in snapshot.children){
                    val name = s.child("Name").getValue()
                    sbRoom.append("${name}\n")
                }
                v.findViewById<TextView>(R.id.clean_name_result)//.setText(sbRoom)
            }


        }
        myRef.addValueEventListener(getCleanData)
        myRef.addListenerForSingleValueEvent(getCleanData)*/
    }
}