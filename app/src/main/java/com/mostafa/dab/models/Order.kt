package com.mostafa.dab.models

data class Order(val driver: String,val heatDgree:String,val weight:String,val paidMoney:String,val note:String,val mixtureType:String){
    constructor( driver: String, heatDgree:String, weight:String, paidMoney:String, note:String, mixtureType:String,id:Int) : this(driver, heatDgree, weight, paidMoney, note, mixtureType)
    constructor( driver: String, heatDgree:String, weight:String, paidMoney:String, note:String, mixtureType:String,projectName:String) : this(driver, heatDgree, weight, paidMoney, note, mixtureType)

}
