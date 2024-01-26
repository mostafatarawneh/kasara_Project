package com.mostafa.dab.Adaptors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mostafa.dab.models.Order
import com.mostafa.dab.R

class RVInfoAdaptor(arrayList: ArrayList<Order>, internal var context : Context): RecyclerView.Adapter<RVInfoAdaptor.MyViewHolder>() {
    internal var arrayList : ArrayList<Order> = ArrayList()

    init {
        this.arrayList = arrayList
    }

    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
        var tv_driverName : TextView =itemView.findViewById(R.id.tv_driver_info_name)
        var tv_loaded : TextView =itemView.findViewById(R.id.tv_loaded_info)
        var tv_payment : TextView =itemView.findViewById(R.id.tv_payment_info)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v =LayoutInflater.from(parent.context).inflate(R.layout.rv_item_info,parent,false)
        return MyViewHolder(v)

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var info = arrayList[position]
        holder.tv_driverName.text=info.driver
        holder.tv_payment.text=info.paidMoney.toString()
        holder.tv_loaded.text=info.weight

    }
}