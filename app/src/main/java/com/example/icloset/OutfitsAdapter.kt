package com.example.icloset

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Environment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import android.widget.ToggleButton
import kotlinx.android.synthetic.main.activity_photo_edit_review.*
import java.io.File
import java.lang.Exception

class OutfitsAdapter(val catList :ArrayList<Outfit>,val con:Context) : RecyclerView.Adapter<OutfitsAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): OutfitsAdapter.ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.cardview_outfit,p0,false)
        return OutfitsAdapter.ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return catList.size
    }

    override fun onBindViewHolder(p0: OutfitsAdapter.ViewHolder, p1: Int) {
        val cat:Outfit = catList[p1]
        try{

            val storageDirectory = Environment.getExternalStorageDirectory().toString()

            val file = File(storageDirectory,cat.thumbnail)
            p0.imageviewname.setImageURI(Uri.parse(file.absolutePath))}
        catch (e: Exception)
        {
            e.printStackTrace()
        }
        p0.imageviewname.setOnLongClickListener {

                val builder = android.app.AlertDialog.Builder(con)
                builder.setTitle("Delete outfit?")
                builder.setMessage("Are you sure?")
                builder.setPositiveButton("Yes"){dialog, which ->
                    catList.removeAt(p1)
                    notifyItemRemoved(p1)
                    val obj = icloset(con)
                    val db = obj.writableDatabase
                    db.execSQL("delete from outfit where Outfit_ID = ?", arrayOf(cat.ID))
                    db.execSQL("delete from outfit_weather where Outfit_ID = ?", arrayOf(cat.ID))
                    db.execSQL("delete from outfit_occasion where Outfit_ID = ?", arrayOf(cat.ID))
                    Toast.makeText(con,"Outfit deleted", Toast.LENGTH_SHORT).show()
                }
                builder.setNegativeButton("No"){
                        dialog, which ->
                    dialog.dismiss()
                }
                builder.show()

            true
        }

        val obj = icloset(con)
        val db = obj.writableDatabase
        val cur = db.rawQuery("select Favorite from outfit where Outfit_ID = ?", arrayOf(cat.ID))
        cur.moveToFirst()

        if(cur.getString(0) == "1"){
            p0.fav_btn.isChecked = true
            p0.fav_btn.setBackgroundResource(R.drawable.fav_heart)
        }

        p0.fav_btn.setOnClickListener {
            val obj = icloset(con)
            val db = obj.writableDatabase

            if(p0.fav_btn.isChecked){

                db.execSQL("update outfit set Favorite = 1 where Outfit_ID = ?", arrayOf(cat.ID))
                p0.fav_btn.setBackgroundResource(R.drawable.fav_heart)
            }
            else {

                db.execSQL("update outfit set Favorite = 0 where Outfit_ID = ?", arrayOf(cat.ID))
                p0.fav_btn.setBackgroundResource(R.drawable.not_fav_heart)
            }

        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageviewname = itemView.findViewById(R.id.iv_outfit) as ImageView
        val fav_btn = itemView.findViewById(R.id.fav_button) as ToggleButton

    }

}