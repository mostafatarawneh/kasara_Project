package com.mostafa.dab.models

data class Driver(val name:String, val vehicleNumber:String, val driverPhoneNumber:Int, val vehicleType: String){
    constructor(name: String,vehicleNumber: String,driverPhoneNumber: Int,vehicleType: String,id:Int) :this(name, vehicleNumber, driverPhoneNumber, vehicleType)
}
