package com.example.mobilehotelmanagementsystem

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

public class PageAdapter(fn:FragmentManager):FragmentPagerAdapter(fn) {

    override fun getItem(page: Int): Fragment {
        //set all the fragment pages here, determine which to be default
        return when (page){
            0->{
                cleaning_list()
            }
            else->{
                food_list()
            }
        }
    }

    override fun getCount(): Int {
        //return according num of fragment
        return 2
    }

    override fun getPageTitle(page: Int): CharSequence? {
        //set tabs title
        return when (page){
            0->"One"
            else->{"Two"}
        }
    }
}