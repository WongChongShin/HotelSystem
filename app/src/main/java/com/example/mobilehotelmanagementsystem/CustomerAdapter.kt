package com.example.mobilehotelmanagementsystem

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomerAdapter(val context: Context, val customerList: ArrayList<CustomerModel>, private val listener: OnItemClickListener):
        RecyclerView.Adapter<CustomerAdapter.Holder>() {

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(customerList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerAdapter.Holder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.customer_row, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return customerList.size
    }

    inner class Holder(view: View?) : RecyclerView.ViewHolder(view!!),View.OnClickListener {
        val room_no = view?.findViewById<TextView>(R.id.customer_room_no_textView)
        val name = view?.findViewById<TextView>(R.id.customer_name_textView)
        val email = view?.findViewById<TextView>(R.id.customer_email_textView)
        val phone = view?.findViewById<TextView>(R.id.customer_phone_textView)
        val checkIn = view?.findViewById<TextView>(R.id.customer_checkIn_textView)
        val checkOut = view?.findViewById<TextView>(R.id.customer_checkOut_textView)
        val customerEdit = view?.findViewById<Button>(R.id.edit_row_customer)
        val customerDelete = view?.findViewById<Button>(R.id.delete_row_customer)

        init{
            customerEdit?.setOnClickListener(this)
            customerDelete?.setOnClickListener(this)
        }

        fun bind(customer: CustomerModel) {
            room_no?.text = customer.room_no
            name?.text = customer.name
            email?.text = customer.email
            phone?.text = customer.phone
            checkIn?.text = customer.checkIn
            checkOut?.text = customer.checkOut

            customerEdit?.isEnabled = true
            customerEdit?.isClickable = true

            customerDelete?.isEnabled = true
            customerDelete?.isClickable = true
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






/*class CustomerAdapter(options: FirebaseRecyclerOptions<CustomerModel>) :
    FirebaseRecyclerAdapter<CustomerModel, CustomerAdapter.CustomerAdapterVH>(options) {

    private val bd: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference: DatabaseReference = bd.getReference("Customer List")
    private var query: Query = databaseReference

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerAdapterVH {
        return CustomerAdapterVH(LayoutInflater.from(parent.context).inflate(R.layout.customer_row, parent, false))
    }

    override fun onBindViewHolder(holder: CustomerAdapterVH, position: Int, model: CustomerModel) {

        var getData = object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {

                for(s in snapshot.children){
                    model.room_no = s.child("Room_No").getValue().toString()
                    holder.room_no.text = model.room_no
                    model.name = s.child("Name").getValue().toString()
                    holder.name.text = model.name
                    model.email = s.child("Email").getValue().toString()
                    holder.email.text = model.email
                    model.phone = s.child("Phone").getValue().toString()
                    holder.phone.text = model.phone
                    model.checkIn = s.child("Check In").getValue().toString()
                    holder.checkIn.text = model.checkIn
                    model.checkOut = s.child("Check Out").getValue().toString()
                    holder.checkOut.text = model.checkOut

                }
            }
        }
        query.addValueEventListener(getData)
        query.addListenerForSingleValueEvent(getData)
    }

    class CustomerAdapterVH(itemView: View): RecyclerView.ViewHolder(itemView) {
        val room_no: TextView = itemView.findViewById(R.id.customer_room_no_textView)
        val name: TextView = itemView.findViewById(R.id.customer_name_textView)
        val email: TextView = itemView.findViewById(R.id.customer_email_textView)
        val phone: TextView = itemView.findViewById(R.id.customer_phone_textView)
        val checkIn: TextView = itemView.findViewById(R.id.customer_checkIn_textView)
        val checkOut: TextView = itemView.findViewById(R.id.customer_checkOut_textView)
    }
}*/