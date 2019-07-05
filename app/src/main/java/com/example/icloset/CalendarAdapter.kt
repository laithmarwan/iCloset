package com.example.icloset

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.support.v7.widget.RecyclerView
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import java.io.File
import java.lang.Exception

class CalendarAdapter(val catList :ArrayList<Outfit>, val con: Context,val act:Activity) : RecyclerView.Adapter<CalendarAdapter.ViewHolder>(){
    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val cat:Outfit = catList[p1]
        try{

            val storageDirectory = Environment.getExternalStorageDirectory().toString()

            val file = File(storageDirectory,cat.thumbnail)
            p0.imageviewname.setImageURI(Uri.parse(file.absolutePath))}
        catch (e: Exception)
        {
            e.printStackTrace()
        }
        p0.imageviewname.setOnClickListener {
            val obj = icloset(con)
            val db = obj.writableDatabase
            val date = "${AppInfo.day}/${AppInfo.month}/${AppInfo.year}"
            db.execSQL("insert into reminders (Date,Outfit_ID) values (?,?)", arrayOf(date,cat.ID))
            act.onBackPressed()
            act.onBackPressed()
        }


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