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

    lateinit var viewPager:ViewPager
    lateinit var tabLayout:TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rooms_service)

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = findViewById<ViewPager>(R.id.viewPager)

        tabLayout.addTab(tabLayout.newTab().setText("Cleaning List"));
        tabLayout.addTab(tabLayout.newTab().setText("Food List"));

        val pageAdapter = PageAdapter(supportFragmentManager)
        viewPager.adapter = pageAdapter

        tabLayout.setupWithViewPager(viewPager)

        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}


        })
    }
}