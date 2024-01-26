package com.mostafa.dab.Adaptors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mostafa.dab.R
import com.mostafa.dab.models.Driver

class AdapotorRvDiverFragment(array:ArrayList<Driver>, internal var context: Context) : RecyclerView.Adapter<AdapotorRvDiverFragment.MyViewHolder>() {
    internal var array : ArrayList<Driver> = ArrayList()
    init {
        this.array=array
    }

    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
        var name : TextView= view.findViewById(R.id.item_tv_rv_driverName)
         var phoneNum :TextView=view.findViewById<TextView>(R.id.item_tv_rv_driverphoneNumber)
             var vehicleNum:TextView = view.findViewById<TextView>(R.id.item_tv_rv_driverVehicleNumber)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var v =LayoutInflater.from(context).inflate(R.layout.mostafa_rec,parent,false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return array.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var driver = array[position]
        holder.name.text=driver.name
        holder.vehicleNum.text=driver.vehicleNumber
        holder.phoneNum.text=driver.driverPhoneNumber.toString()

    }

}