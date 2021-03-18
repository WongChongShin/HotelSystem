package com.example.mobilehotelmanagementsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class rooms_service : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rooms_service)

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val tabClean = findViewById<TabItem>(R.id.cleaning_list_tab)
        val tabFood = findViewById<TabItem>(R.id.food_list_tab)
        val viewPager = findViewById<ViewPager>(R.id.viewPager)

        val pageAdapter = PageAdapter(supportFragmentManager)
        viewPager.adapter = pageAdapter

        tabLayout.setupWithViewPager(viewPager)


    }
}