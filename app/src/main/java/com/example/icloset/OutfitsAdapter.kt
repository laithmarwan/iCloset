package com.example.icloset

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
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

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageviewname = itemView.findViewById(R.id.iv_outfit) as ImageView

    }

}