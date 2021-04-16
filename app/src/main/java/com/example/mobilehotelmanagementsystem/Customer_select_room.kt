package com.example.mobilehotelmanagementsystem

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class Customer_select_room : AppCompatActivity(),Customer_room_adapter.OnItemClickListener {

    private val roomNoArr = ArrayList<String>()
    private val roomDescArr = ArrayList<String>()
    private val roomPriceArr = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_select_room)


        val database = FirebaseDatabase.getInstance();
        val roomDatabaseRef = database.getReference("Room booking");
        var roomList: ArrayList<customer_room_list>

        //var recyclerView : RecyclerView?=null
        var recycleView: RecyclerView

        recycleView=findViewById(R.id.customer_roomList)
        recycleView?.setHasFixedSize(true)
        recycleView?.layoutManager = LinearLayoutManager(this)

        roomList= arrayListOf<customer_room_list>()



        roomDatabaseRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(snapshot: DataSnapshot) {


                if(snapshot!!.exists()){

                    for (s in snapshot.children){
                        val custRoomId = s.child("RoomNo").getValue().toString()
                        val custRoomDesc = s.child("Description").getValue().toString()
                        val custRoomPrice = s.child("OriginalPrice").getValue().toString()
                        val room = s.getValue(customer_room_list::class.java)
                        room!!.roomNo=custRoomId
                        roomNoArr.add(custRoomId)
                        room!!.roomDesc=custRoomDesc
                        roomDescArr.add(custRoomDesc)
                        room!!.roomPrice=custRoomPrice
                        roomPriceArr.add(custRoomPrice)
                        roomList.add(room)

                    }

                    val adapter = Customer_room_adapter(this@Customer_select_room, roomList,this@Customer_select_room)

                    recycleView.setAdapter(adapter)

                }

            }
        })


       /* *//*val bookRoom100 = findViewById<Button>(R.id.book_room_100_btn)
        val bookRoom101 = findViewById<Button>(R.id.book_room_101_btn)
        val bookRoom102 = findViewById<Button>(R.id.book_room_102_btn)
        val bookRoom103 = findViewById<Button>(R.id.book_room_103_btn)
        val bookRoom104 = findViewById<Button>(R.id.book_room_104_btn)
        val bookRoom105 = findViewById<Button>(R.id.book_room_105_btn)
        val bookRoom106 = findViewById<Button>(R.id.book_room_106_btn)
        val bookRoom107 = findViewById<Button>(R.id.book_room_107_btn)*//*



        //------------------------checking date


        val getRoomCheckOutData = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {

                for (s in snapshot.children) {
                    val roomCheckOutDate = s.child("CheckOut").getValue().toString()
                    val sdf = SimpleDateFormat("dd/MM/yyyy")
                    val strDate: Date = sdf.parse(roomCheckOutDate)
                    if (System.currentTimeMillis()>strDate.time) {

                        val roomNoLength= roomNoArr!!.size
                        for(i in 0 until roomNoLength) {
                            if (roomNoArr[i].equals("100")) {
                                roomDatabaseRef.child(roomNoArr[i]).child("Status").setValue("empty")

                            } else if (roomNoArr[i].equals("101")) {
                                roomDatabaseRef.child(roomNoArr[i]).child("Status").setValue("empty")

                            } else if (roomNoArr[i].equals("102")) {
                                roomDatabaseRef.child(roomNoArr[i]).child("Status").setValue("empty")

                            } else if (roomNoArr[i].equals("103")) {
                                roomDatabaseRef.child(roomNoArr[i]).child("Status").setValue("empty")

                            } else if (roomNoArr[i].equals("104")) {
                                roomDatabaseRef.child(roomNoArr[i]).child("Status").setValue("empty")

                            } else if (roomNoArr[i].equals("105")) {
                                roomDatabaseRef.child(roomNoArr[i]).child("Status").setValue("empty")

                            } else if (roomNoArr[i].equals("106")) {
                                roomDatabaseRef.child(roomNoArr[i]).child("Status").setValue("empty")

                            } else if (roomNoArr[i].equals("107")) {
                                roomDatabaseRef.child(roomNoArr[i]).child("Status").setValue("empty")

                            }

                        }
                    }
                    else{
                        val roomNoLength= roomNoArr!!.size
                        for(i in 0 until roomNoLength) {
                            if (roomNoArr[i].equals("100")) {
                                roomDatabaseRef.child(roomNoArr[i]).child("Status").setValue("full")

                            } else if (roomNoArr[i].equals("101")) {
                                roomDatabaseRef.child(roomNoArr[i]).child("Status").setValue("full")

                            } else if (roomNoArr[i].equals("102")) {
                                roomDatabaseRef.child(roomNoArr[i]).child("Status").setValue("full")

                            } else if (roomNoArr[i].equals("103")) {
                                roomDatabaseRef.child(roomNoArr[i]).child("Status").setValue("full")

                            } else if (roomNoArr[i].equals("104")) {
                                roomDatabaseRef.child(roomNoArr[i]).child("Status").setValue("full")

                            } else if (roomNoArr[i].equals("105")) {
                                roomDatabaseRef.child(roomNoArr[i]).child("Status").setValue("full")

                            } else if (roomNoArr[i].equals("106")) {
                                roomDatabaseRef.child(roomNoArr[i]).child("Status").setValue("full")

                            } else if (roomNoArr[i].equals("107")) {
                                roomDatabaseRef.child(roomNoArr[i]).child("Status").setValue("full")

                            }

                        }
                    }

                }



            }
        }



        val checkOutQuery: Query = roomDatabaseRef.orderByChild("CheckOut")

        checkOutQuery.addValueEventListener(getRoomCheckOutData)
        checkOutQuery.addListenerForSingleValueEvent(getRoomCheckOutData)


        //------------------------checking status full------------------------------
        val roomStatus = StringBuilder()
        val roomStatusArr = ArrayList<String>()
        val q: Query = roomDatabaseRef.orderByChild("Status").equalTo("full")


        val getRoomData = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {

                for (s in snapshot.children) {
                    val custRoomId = s.child("RoomNo").getValue().toString()
                    roomStatus.append("${custRoomId}\n")
                    roomStatusArr.add(custRoomId)

                }
                val statusLength= roomStatusArr!!.size
                for(i in 0 until statusLength) {
                    if (roomStatusArr[i].equals("100")) {
                        bookRoom100.setBackgroundColor(Color.RED);
                        bookRoom100.setText("Full")
                        bookRoom100.setTextColor(Color.BLACK);
                        bookRoom100.isEnabled = false
                        bookRoom100.isClickable = false
                    }
                    else if(roomStatusArr[i].equals("101")) {
                        bookRoom101.setBackgroundColor(Color.RED);
                        bookRoom101.setText("Full");
                        bookRoom101.setTextColor(Color.BLACK);
                        bookRoom101.isEnabled = false
                        bookRoom101.isClickable = false
                    }
                    else if(roomStatusArr[i].equals("102")) {
                        bookRoom102.setBackgroundColor(Color.RED);
                        bookRoom102.setText("Full");
                        bookRoom102.setTextColor(Color.BLACK);
                        bookRoom102.isEnabled = false
                        bookRoom102.isClickable = false
                    }
                    else if(roomStatusArr[i].equals("103")) {
                        bookRoom103.setBackgroundColor(Color.RED);
                        bookRoom103.setText("Full");
                        bookRoom103.setTextColor(Color.BLACK);
                        bookRoom103.isEnabled = false
                        bookRoom103.isClickable = false
                    }
                    else if(roomStatusArr[i].equals("104")) {
                        bookRoom104.setBackgroundColor(Color.RED);
                        bookRoom104.setText("Full");
                        bookRoom104.setTextColor(Color.BLACK);
                        bookRoom104.isEnabled = false
                        bookRoom104.isClickable = false
                    }
                    else if(roomStatusArr[i].equals("105")) {
                        bookRoom105.setBackgroundColor(Color.RED);
                        bookRoom105.setText("Full");
                        bookRoom105.setTextColor(Color.BLACK);
                        bookRoom105.isEnabled = false
                        bookRoom105.isClickable = false
                    }
                    else if(roomStatusArr[i].equals("106")) {
                        bookRoom106.setBackgroundColor(Color.RED);
                        bookRoom106.setText("Full");
                        bookRoom106.setTextColor(Color.BLACK);
                        bookRoom106.isEnabled = false
                        bookRoom106.isClickable = false
                    }
                    else if(roomStatusArr[i].equals("107")) {
                        bookRoom107.setBackgroundColor(Color.RED);
                        bookRoom107.setText("Full");
                        bookRoom107.setTextColor(Color.BLACK);
                        bookRoom107.isEnabled = false
                        bookRoom107.isClickable = false
                    }


                }


            }
        }
        q.addValueEventListener(getRoomData)
        q.addListenerForSingleValueEvent(getRoomData)



        //------------------------checking status empty------------------------------
        val roomStatusEmpty = StringBuilder()
        val roomStatusEmptyArr = ArrayList<String>()
        val getRoomDataEmpty = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {

                for (s in snapshot.children) {
                    val custRoomId = s.child("RoomNo").getValue().toString()
                    roomStatusEmpty.append("${custRoomId}\n")
                    roomStatusEmptyArr.add(custRoomId)

                }
                val statusEmptyLength= roomStatusEmptyArr!!.size
                for(i in 0 until statusEmptyLength) {
                    if (roomStatusEmptyArr[i].equals("100")) {
                        bookRoom100.setBackgroundColor(Color.parseColor("#ff99cc00"));
                        bookRoom100.setText("Book")
                        bookRoom100.setTextColor(Color.BLACK);
                        bookRoom100.isEnabled = true
                        bookRoom100.isClickable = true
                    } else if (roomStatusEmptyArr[i].equals("101")) {
                        bookRoom101.setBackgroundColor(Color.parseColor("#ff99cc00"));
                        bookRoom101.setText("Book");
                        bookRoom101.setTextColor(Color.BLACK);
                        bookRoom101.isEnabled = true
                        bookRoom101.isClickable = true
                    } else if (roomStatusEmptyArr[i].equals("102")) {
                        bookRoom102.setBackgroundColor(Color.parseColor("#ff99cc00"));
                        bookRoom102.setText("Book");
                        bookRoom102.setTextColor(Color.BLACK);
                        bookRoom102.isEnabled = true
                        bookRoom102.isClickable = true
                    } else if (roomStatusEmptyArr[i].equals("103")) {
                        bookRoom103.setBackgroundColor(Color.parseColor("#ff99cc00"));
                        bookRoom103.setText("Book");
                        bookRoom103.setTextColor(Color.BLACK);
                        bookRoom103.isEnabled = true
                        bookRoom103.isClickable = true
                    } else if (roomStatusEmptyArr[i].equals("104")) {
                        bookRoom104.setBackgroundColor(Color.parseColor("#ff99cc00"));
                        bookRoom104.setText("Book");
                        bookRoom104.setTextColor(Color.BLACK);
                        bookRoom104.isEnabled = true
                        bookRoom104.isClickable = true
                    } else if (roomStatusEmptyArr[i].equals("105")) {
                        bookRoom105.setBackgroundColor(Color.parseColor("#ff99cc00"));
                        bookRoom105.setText("Book");
                        bookRoom105.setTextColor(Color.BLACK);
                        bookRoom105.isEnabled = true
                        bookRoom105.isClickable = true
                    } else if (roomStatusEmptyArr[i].equals("106")) {
                        bookRoom106.setBackgroundColor(Color.parseColor("#ff99cc00"));
                        bookRoom106.setText("Book");
                        bookRoom106.setTextColor(Color.BLACK);
                        bookRoom106.isEnabled = true
                        bookRoom106.isClickable = true
                    } else if (roomStatusEmptyArr[i].equals("107")) {
                        bookRoom107.setBackgroundColor(Color.parseColor("#ff99cc00"));
                        bookRoom107.setText("Book");
                        bookRoom107.setTextColor(Color.BLACK);
                        bookRoom107.isEnabled = true
                        bookRoom107.isClickable = true
                    }


                }


            }
        }

        val qEmpty: Query = roomDatabaseRef.orderByChild("Status").equalTo("empty")

        qEmpty.addValueEventListener(getRoomDataEmpty)
        qEmpty.addListenerForSingleValueEvent(getRoomDataEmpty)







        val roomBackBtn: ImageView = findViewById<ImageView>(R.id.select_room_back);

        roomBackBtn.setOnClickListener {
            val backCustomerMain = Intent(this, CustomerMainActivity::class.java)

            startActivity(backCustomerMain)
        }

        val handler = Handler(Looper.getMainLooper())
        val someThread = Runnable {
            //----------------------button 100-107----------------------------------
            bookRoom100.setOnClickListener {

                val customerRoom100Intent = Intent(this, Customer_select_detail::class.java)

                val customerNo100 = findViewById<TextView>(R.id.customer_room_no_100).text
                val customerDesc100 = findViewById<TextView>(R.id.customer_room_description_100).text
                val customerPrice100 = findViewById<TextView>(R.id.customer_price_100).text
                customerRoom100Intent.putExtra("room_no", customerNo100.toString());
                customerRoom100Intent.putExtra("room_desc", customerDesc100.toString());
                customerRoom100Intent.putExtra("room_price", customerPrice100.toString());
                startActivity(customerRoom100Intent)
            }



            bookRoom101.setOnClickListener {

                val customerRoom101Intent = Intent(this, Customer_select_detail::class.java)
                val customerNo101 = findViewById<TextView>(R.id.customer_room_no_101).text
                val customerDesc101 = findViewById<TextView>(R.id.customer_room_description_101).text
                val customerPrice101 = findViewById<TextView>(R.id.customer_price_101).text
                customerRoom101Intent.putExtra("room_no", customerNo101.toString());
                customerRoom101Intent.putExtra("room_desc", customerDesc101.toString());
                customerRoom101Intent.putExtra("room_price", customerPrice101.toString());
                startActivity(customerRoom101Intent)
            }


            bookRoom102.setOnClickListener {

                val customerRoom102Intent = Intent(this, Customer_select_detail::class.java)
                val customerNo102 = findViewById<TextView>(R.id.customer_room_no_102).text
                val customerDesc102 = findViewById<TextView>(R.id.customer_room_description_102).text
                val customerPrice102 = findViewById<TextView>(R.id.customer_price_102).text
                customerRoom102Intent.putExtra("room_no", customerNo102.toString());
                customerRoom102Intent.putExtra("room_desc", customerDesc102.toString());
                customerRoom102Intent.putExtra("room_price", customerPrice102.toString());
                startActivity(customerRoom102Intent)
            }


            bookRoom103.setOnClickListener {

                val customerRoom103Intent = Intent(this, Customer_select_detail::class.java)
                val customerNo103 = findViewById<TextView>(R.id.customer_room_no_103).text
                val customerDesc103 = findViewById<TextView>(R.id.customer_room_description_103).text
                val customerPrice103 = findViewById<TextView>(R.id.customer_price_103).text
                customerRoom103Intent.putExtra("room_no", customerNo103.toString());
                customerRoom103Intent.putExtra("room_desc", customerDesc103.toString());
                customerRoom103Intent.putExtra("room_price", customerPrice103.toString());
                startActivity(customerRoom103Intent)
            }


            bookRoom104.setOnClickListener {

                val customerRoom104Intent = Intent(this, Customer_select_detail::class.java)
                val customerNo104 = findViewById<TextView>(R.id.customer_room_no_104).text
                val customerDesc104 = findViewById<TextView>(R.id.customer_room_description_104).text
                val customerPrice104 = findViewById<TextView>(R.id.customer_price_104).text
                customerRoom104Intent.putExtra("room_no", customerNo104.toString());
                customerRoom104Intent.putExtra("room_desc", customerDesc104.toString());
                customerRoom104Intent.putExtra("room_price", customerPrice104.toString());
                startActivity(customerRoom104Intent)
            }


            bookRoom105.setOnClickListener {

                val customerRoom105Intent = Intent(this, Customer_select_detail::class.java)
                val customerNo105 = findViewById<TextView>(R.id.customer_room_no_105).text
                val customerDesc105 = findViewById<TextView>(R.id.customer_room_description_105).text
                val customerPrice105 = findViewById<TextView>(R.id.customer_price_105).text
                customerRoom105Intent.putExtra("room_no", customerNo105.toString());
                customerRoom105Intent.putExtra("room_desc", customerDesc105.toString());
                customerRoom105Intent.putExtra("room_price", customerPrice105.toString());
                startActivity(customerRoom105Intent)
            }


            bookRoom106.setOnClickListener {

                val customerRoom106Intent = Intent(this, Customer_select_detail::class.java)
                val customerNo106 = findViewById<TextView>(R.id.customer_room_no_106).text
                val customerDesc106 = findViewById<TextView>(R.id.customer_room_description_106).text
                val customerPrice106 = findViewById<TextView>(R.id.customer_price_106).text
                customerRoom106Intent.putExtra("room_no", customerNo106.toString());
                customerRoom106Intent.putExtra("room_desc", customerDesc106.toString());
                customerRoom106Intent.putExtra("room_price", customerPrice106.toString());
                startActivity(customerRoom106Intent)
            }

            bookRoom107.setOnClickListener {

                val customerRoom107Intent = Intent(this, Customer_select_detail::class.java)
                val customerNo107 = findViewById<TextView>(R.id.customer_room_no_107).text
                val customerDesc107 = findViewById<TextView>(R.id.customer_room_description_107).text
                val customerPrice107 = findViewById<TextView>(R.id.customer_price_107).text
                customerRoom107Intent.putExtra("room_no", customerNo107.toString());
                customerRoom107Intent.putExtra("room_desc", customerDesc107.toString());
                customerRoom107Intent.putExtra("room_price", customerPrice107.toString());
                startActivity(customerRoom107Intent)
            }
        }
        handler.postDelayed(someThread, 4000)



    }



*/

}

    override fun onItemClick(position: Int) {
        val customerRoomIntent = Intent(this, Customer_select_detail::class.java)
        customerRoomIntent.putExtra("room_no", roomNoArr[position].toString());
        customerRoomIntent.putExtra("room_desc", roomDescArr[position].toString());
        customerRoomIntent.putExtra("room_price", roomPriceArr[position].toString());

        startActivity(customerRoomIntent)
    }
}