package com.example.mobilehotelmanagementsystem

import java.util.*
import java.io.Serializable;

class CustomerService: Serializable {
    public var name:String = "default Name"
    public var price:Float = 0.00f
    public var description:String = "default description"
    public var picture:Int = 0
    public var minTime24: Int = 0
    public var maxTime24: Int = 23
    public var availability: Boolean = true;
    public var serviceType :String = "Food Service"



    fun checkTimeAvailable(): Boolean{
        val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

        if (  currentHour >= minTime24 && currentHour < maxTime24 ){
            return true
        }else{
            return false
        }
    }


}

