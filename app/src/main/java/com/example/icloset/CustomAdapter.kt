package com.example.icloset

import android.support.design.widget.TabLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class CustomAdapter(val catList :ArrayList<Categories>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.cardview_item,p0,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return catList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val cat:Categories = catList[p1]
        p0.imageviewname.setImageBitmap(cat.thumbnail)

    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageviewname = itemView.findViewById(R.id.imageView) as ImageView

    }
}