package com.example.mobilehotelmanagementsystem

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

/*class CleanServiceAdapter(options: FirebaseRecyclerOptions<CleanServiceModel>) :
    FirebaseRecyclerAdapter<CleanServiceModel, CleanServiceAdapter.CleanServiceAdapterVH>(options) {

    //private val db = FirebaseDatabase.getInstance()
    //private val databaseReference = db.getReference("Cleaning List")
    private val db: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference: DatabaseReference = db.getReference("Cleaning List")

    val query: Query = databaseReference

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CleanServiceAdapterVH {
        return CleanServiceAdapterVH(LayoutInflater.from(parent.context).inflate(R.layout.row_clean_service, parent, false))
    }


    override fun onBindViewHolder(holder: CleanServiceAdapterVH, position: Int, model: CleanServiceModel) {


        var getCleanData = object : ValueEventListener {

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {

                for(s in snapshot.children){
                    /*holder.userRoom.text = model.userRoom.toString()
                    holder.userName.text = model.userName.toString()
                    holder.userPhone.text = model.userPhone.toString()
                    val name = s.child("Name")
                    sbRoom.append("${name}\n")
                    val phone = s.child("Phone").getValue()
                    sbRoom.append("${phone}\n")*/

                    holder.userName.text = s.child("Name").getValue().toString()
                    holder.userPhone.text = s.child("Phone").getValue().toString()
                }

            }

        }
        databaseReference.addValueEventListener(getCleanData)
        databaseReference.addListenerForSingleValueEvent(getCleanData)

    }

    class CleanServiceAdapterVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var userRoom: TextView = itemView.findViewById(R.id.row_clean_room)
        var userName: TextView = itemView.findViewById(R.id.row_clean_name)
        var userPhone: TextView = itemView.findViewById(R.id.row_clean_phone)


    }

    /*var getCleanData = object : ValueEventListener {


        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }

        override fun onDataChange(snapshot: DataSnapshot) {
            var sbRoom = StringBuilder()

            for(s in snapshot.children){
                val name = s.child("Name").getValue()
                sbRoom.append("${name}\n")
                val phone = s.child("Phone").getValue()
                sbRoom.append("${phone}\n")
            }

        }

    }*/

}*/

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
        val cleanRoom = view?.findViewById<TextView>(R.id.row_clean_room_tv)
        val cleanName = view?.findViewById<TextView>(R.id.row_clean_name_tv)
        val cleanPhone = view?.findViewById<TextView>(R.id.row_clean_phone_tv)
        val cleanEdit = view?.findViewById<Button>(R.id.clean_edit_button)
        val cleanDelete = view?.findViewById<Button>(R.id.clean_delete_button)

        init{
            cleanEdit?.setOnClickListener(this)
            cleanDelete?.setOnClickListener(this)
        }

        fun bind(clean: CleanServiceModel) {
            cleanRoom?.text = clean.userRoom
            cleanName?.text = clean.userName
            cleanPhone?.text = clean.userPhone

            cleanEdit?.isEnabled = true
            cleanEdit?.isClickable = true

            cleanDelete?.isEnabled = true
            cleanDelete?.isClickable = true
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