package com.example.icloset

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.support.design.widget.TabLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.io.File
import java.lang.Exception

class CustomAdapter(val catList :ArrayList<Categories>,val con:Context) : RecyclerView.Adapter<CustomAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.cardview_item,p0,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return catList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {

        try{
        val cat:Categories = catList[p1]
        val storageDirectory = Environment.getExternalStorageDirectory().toString()

        val file = File(storageDirectory,cat.thumbnail)
        p0.imageviewname.setImageURI(Uri.parse(file.absolutePath))}
        catch (e: Exception)
        {
            e.printStackTrace()
        }


        p0.imageviewname.setOnClickListener {
            if(AppInfo.act == ""){
                Toast.makeText(con,"Clicked",Toast.LENGTH_SHORT).show()}
            else
            {
                //
            }
        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageviewname = itemView.findViewById(R.id.imageView) as ImageView

    }
}