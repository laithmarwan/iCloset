package com.example.icloset

import android.support.design.widget.TabLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class CustomAdapter(val catList :ArrayList<Categories>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.list_layout,p0,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return catList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val cat : Categories = catList[p1]
        p0.textViewName.text = cat.name

    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textViewName = itemView.findViewById(R.id.tv1) as TextView
        val textViewAddress = itemView.findViewById(R.id.tv2) as TextView
    }
}