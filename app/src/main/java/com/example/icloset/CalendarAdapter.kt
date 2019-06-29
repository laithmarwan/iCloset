package com.example.icloset

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

class CalendarAdapter(val catList :ArrayList<Outfit>) : RecyclerView.Adapter<CalendarAdapter.ViewHolder>(){
    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val cat:Outfit = catList[p1]
        p0.imageviewname.setImageBitmap(cat.thumbnail)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CalendarAdapter.ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.cardview_item,p0,false)
        return CalendarAdapter.ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return catList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageviewname = itemView.findViewById(R.id.imageView) as ImageView

    }

}