package com.example.icloset

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

class OutfitsAdapter(val catList :ArrayList<Outfit>) : RecyclerView.Adapter<OutfitsAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): OutfitsAdapter.ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.cardview_outfit,p0,false)
        return OutfitsAdapter.ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return catList.size
    }

    override fun onBindViewHolder(p0: OutfitsAdapter.ViewHolder, p1: Int) {
        val cat:Outfit = catList[p1]
        p0.imageviewname.setImageBitmap(cat.thumbnail)

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageviewname = itemView.findViewById(R.id.iv_outfit) as ImageView

    }

}