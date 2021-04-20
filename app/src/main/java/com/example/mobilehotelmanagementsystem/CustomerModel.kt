package com.example.mobilehotelmanagementsystem

class CustomerModel {
    var room_no: String? =null
    var name: String? =null
    var email: String? =null
    var phone: String? =null
    var checkIn: String? = null
    var checkOut: String? = null

    constructor()

    constructor(room_no: String?, name: String?, email: String?, phone: String?, checkIn: String?, checkOut: String?) {
        this.room_no = room_no
        this.name = name
        this.email = email
        this.phone = phone
        this.checkIn = checkIn
        this.checkOut = checkOut
    }
}