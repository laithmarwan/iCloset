package com.example.icloset

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.media.Image
import android.net.Uri
import android.os.Environment
import android.support.design.widget.TabLayout
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.io.File
import java.lang.Exception

class ReminderAdapter(private val catList :ArrayList<Reminder>, private val con:Context) : RecyclerView.Adapter<ReminderAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.cardview_calendar, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return catList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val cat: Reminder = catList[p1]
        try {

            val storageDirectory = Environment.getExternalStorageDirectory().toString()

            val obj = icloset(con)
            val db = obj.readableDatabase
            val cur = db.rawQuery("select Outfit_image from outfit where Outfit_ID = ?", arrayOf(cat.outfit_id))
            cur.moveToFirst()
            val file = File(storageDirectory, cur.getString(0))

            p0.imageviewname.setImageURI(Uri.parse(file.absolutePath))
        } catch (e: Exception) {
            e.printStackTrace()
        }

        p0.textView.text = cat.reminder_date
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageviewname = itemView.findViewById(R.id.calendar_imageview) as ImageView
        val textView = itemView.findViewById(R.id.calendar_textview) as TextView
    }
}
