package com.example.mobilehotelmanagementsystem

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class food_list : Fragment(),FoodServiceAdapter.OnItemClickListener {

    //Retrieve data to recycler view declaraction
    private val foodRoomArr = ArrayList<String>()
    private val foodNameArr = ArrayList<String>()
    private val foodPhoneArr = ArrayList<String>()
    private val db = FirebaseDatabase.getInstance()
    private val databaseReference = db.getReference("Food List")
    private lateinit var foodServiceList: ArrayList<FoodServiceModel>
    private lateinit var recyclerView: RecyclerView
    var foodServiceAdapter: FoodServiceAdapter? = null
    var food_search_input: EditText? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_food_list,container,false)

        val btnAddFood = v.findViewById<ImageView>(R.id.add_food_fab)

        //Recycler View stuff
        recyclerView = v.findViewById(R.id.food_rv)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(this.context)
        foodServiceList= arrayListOf<FoodServiceModel>()

        //add new food service intent
        btnAddFood.setOnClickListener(){

            val addNewFoodIntent = Intent(getActivity(), add_new_food_service::class.java)
            startActivity(addNewFoodIntent)
        }

        //search data
        food_search_input= v.findViewById<EditText>(R.id.editSearch4)
        food_search_input!!.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(word: CharSequence, start: Int,
                                       before: Int, count: Int) {

            }

        })
        
        //get data
        var d = databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(snapshot: DataSnapshot) {

                foodServiceList.clear()
                if (food_search_input!!.text.toString() == "") {
                    for (s in snapshot.children) {

                        val foodRoom = s.child("fRoom").getValue().toString()
                        val foodName = s.child("fName").getValue().toString()
                        val foodPhone = s.child("fPhone").getValue().toString()
                        val fs = s.getValue(FoodServiceModel::class.java)

                        fs!!.userfoodRoom = foodRoom
                        foodRoomArr.add(foodRoom)
                        fs!!.userfoodName = foodName
                        foodNameArr.add(foodName)
                        fs!!.userfoodPhone = foodPhone
                        foodPhoneArr.add(foodPhone)
                        foodServiceList.add(fs)

                    }
                    val adapter = FoodServiceAdapter(this@food_list, foodServiceList, this@food_list)

                    recyclerView.setAdapter(adapter)
                }
            }
        })


        return v
    }

    override fun onItemClick(position: Int) {
        val foodIntent = Intent(getActivity(), edit_food_service::class.java);
        foodIntent.putExtra("fRoom", foodRoomArr[position].toString());
        foodIntent.putExtra("fName", foodNameArr[position].toString());
        foodIntent.putExtra("fPhone", foodPhoneArr[position].toString());

        startActivity(foodIntent)
    }
}