package com.mostafa.dab.Adaptors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mostafa.dab.models.Project
import com.mostafa.dab.R

class RecyclerAdabtor(arrayList: ArrayList<Project>, internal var context : Context, val clickListener: (Project) -> Unit): RecyclerView.Adapter<RecyclerAdabtor.MyViewHolder>() {

    internal var arrayList : ArrayList<Project> = ArrayList()

    init {
        this.arrayList = arrayList
    }


    inner class MyViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        var tv_nameProject : TextView =itemView.findViewById(R.id.itemRec_projectName)




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       var v= LayoutInflater.from(context).inflate(R.layout.item_recyclerview,parent,false)

        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            var project = arrayList[position]
            holder.tv_nameProject.text=project.name
        holder?.itemView!!.setOnClickListener { clickListener(project) }


    }
}