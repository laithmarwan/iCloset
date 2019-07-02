package com.example.icloset

import android.app.Activity
import android.app.Activity.RESULT_OK
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

class CustomAdapter(val catList :ArrayList<Categories>,val con:Activity) : RecyclerView.Adapter<CustomAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.cardview_item,p0,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return catList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val cat:Categories = catList[p1]
        try{

        val storageDirectory = Environment.getExternalStorageDirectory().toString()

        val file = File(storageDirectory,cat.thumbnail)
        p0.imageviewname.setImageURI(Uri.parse(file.absolutePath))}
        catch (e: Exception)
        {
            e.printStackTrace()
        }


        p0.imageviewname.setOnClickListener {
            if(AppInfo.act != "outfit"){
                Toast.makeText(con,"Clicked",Toast.LENGTH_SHORT).show()}
            else
            {
                AppInfo.itemID = cat.ID
                con.setResult(RESULT_OK)
                con.finish()
            }
        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageviewname = itemView.findViewById(R.id.imageView) as ImageView

    }
}