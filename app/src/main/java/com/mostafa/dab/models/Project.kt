package com.mostafa.dab.models

data class Project(var name : String,var destination:String,var destinationPhoneNumber:Int){
    constructor(name: String,destination: String,destinationPhoneNumber: Int, id : Int):this(name,destination,destinationPhoneNumber)
}
