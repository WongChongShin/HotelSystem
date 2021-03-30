package com.example.mobilehotelmanagementsystem

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Customer_select_room : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_select_room)

        val roomBackBtn: ImageView = findViewById<ImageView>(R.id.select_room_back);

        roomBackBtn.setOnClickListener{
            val backCustomerMain = Intent(this, CustomerMainActivity::class.java)
            startActivity(backCustomerMain)
        }

        val bookRoom100 = findViewById<Button>(R.id.book_room_100_btn)

        bookRoom100 .setOnClickListener{

            val customerRoom100Intent = Intent(this, Customer_select_detail::class.java)
            val customerNo100 = findViewById<TextView>(R.id.customer_room_no_100).text
            val customerDesc100 = findViewById<TextView>(R.id.customer_room_description_100).text
            val customerPrice100 = findViewById<TextView>(R.id.customer_price_100).text
            customerRoom100Intent.putExtra("room_no", customerNo100.toString());
            customerRoom100Intent.putExtra("room_desc", customerDesc100.toString());
            customerRoom100Intent.putExtra("room_price", customerPrice100.toString());
            startActivity(customerRoom100Intent)
        }

        val bookRoom101 = findViewById<Button>(R.id.book_room_101_btn)

        bookRoom101 .setOnClickListener{

            val customerRoom101Intent = Intent(this, Customer_select_detail::class.java)
            val customerNo101 = findViewById<TextView>(R.id.customer_room_no_101).text
            val customerDesc101 = findViewById<TextView>(R.id.customer_room_description_101).text
            val customerPrice101 = findViewById<TextView>(R.id.customer_price_101).text
            customerRoom101Intent.putExtra("room_no", customerNo101.toString());
            customerRoom101Intent.putExtra("room_desc", customerDesc101.toString());
            customerRoom101Intent.putExtra("room_price", customerPrice101.toString());
            startActivity(customerRoom101Intent)
        }

        val bookRoom102 = findViewById<Button>(R.id.book_room_102_btn)

        bookRoom102 .setOnClickListener{

            val customerRoom102Intent = Intent(this, Customer_select_detail::class.java)
            val customerNo102 = findViewById<TextView>(R.id.customer_room_no_102).text
            val customerDesc102 = findViewById<TextView>(R.id.customer_room_description_102).text
            val customerPrice102 = findViewById<TextView>(R.id.customer_price_102).text
            customerRoom102Intent.putExtra("room_no", customerNo102.toString());
            customerRoom102Intent.putExtra("room_desc", customerDesc102.toString());
            customerRoom102Intent.putExtra("room_price", customerPrice102.toString());
            startActivity(customerRoom102Intent)
        }

        val bookRoom103 = findViewById<Button>(R.id.book_room_103_btn)

        bookRoom103 .setOnClickListener{

            val customerRoom103Intent = Intent(this, Customer_select_detail::class.java)
            val customerNo103 = findViewById<TextView>(R.id.customer_room_no_103).text
            val customerDesc103 = findViewById<TextView>(R.id.customer_room_description_103).text
            val customerPrice103 = findViewById<TextView>(R.id.customer_price_103).text
            customerRoom103Intent.putExtra("room_no", customerNo103.toString());
            customerRoom103Intent.putExtra("room_desc", customerDesc103.toString());
            customerRoom103Intent.putExtra("room_price", customerPrice103.toString());
            startActivity(customerRoom103Intent)
        }

        val bookRoom104 = findViewById<Button>(R.id.book_room_104_btn)

        bookRoom104 .setOnClickListener{

            val customerRoom104Intent = Intent(this, Customer_select_detail::class.java)
            val customerNo104 = findViewById<TextView>(R.id.customer_room_no_104).text
            val customerDesc104 = findViewById<TextView>(R.id.customer_room_description_104).text
            val customerPrice104 = findViewById<TextView>(R.id.customer_price_104).text
            customerRoom104Intent.putExtra("room_no", customerNo104.toString());
            customerRoom104Intent.putExtra("room_desc", customerDesc104.toString());
            customerRoom104Intent.putExtra("room_price", customerPrice104.toString());
            startActivity(customerRoom104Intent)
        }

        val bookRoom105 = findViewById<Button>(R.id.book_room_105_btn)

        bookRoom105 .setOnClickListener{

            val customerRoom105Intent = Intent(this, Customer_select_detail::class.java)
            val customerNo105 = findViewById<TextView>(R.id.customer_room_no_105).text
            val customerDesc105 = findViewById<TextView>(R.id.customer_room_description_105).text
            val customerPrice105 = findViewById<TextView>(R.id.customer_price_105).text
            customerRoom105Intent.putExtra("room_no", customerNo105.toString());
            customerRoom105Intent.putExtra("room_desc", customerDesc105.toString());
            customerRoom105Intent.putExtra("room_price", customerPrice105.toString());
            startActivity(customerRoom105Intent)
        }

        val bookRoom106 = findViewById<Button>(R.id.book_room_106_btn)

        bookRoom106 .setOnClickListener{

            val customerRoom106Intent = Intent(this, Customer_select_detail::class.java)
            val customerNo106 = findViewById<TextView>(R.id.customer_room_no_106).text
            val customerDesc106 = findViewById<TextView>(R.id.customer_room_description_106).text
            val customerPrice106 = findViewById<TextView>(R.id.customer_price_106).text
            customerRoom106Intent.putExtra("room_no", customerNo106.toString());
            customerRoom106Intent.putExtra("room_desc", customerDesc106.toString());
            customerRoom106Intent.putExtra("room_price", customerPrice106.toString());
            startActivity(customerRoom106Intent)
        }

        val bookRoom107 = findViewById<Button>(R.id.book_room_107_btn)

        bookRoom107 .setOnClickListener{

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
}