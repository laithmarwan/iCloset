package com.example.icloset

import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Environment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import kotlinx.android.synthetic.main.activity_photo_edit_review.*
import java.io.File
import java.lang.Exception

class OutfitsAdapter(val catList :ArrayList<Outfit>,val con:Context) : RecyclerView.Adapter<OutfitsAdapter.ViewHolder>(){
    private lateinit var myDialog:Dialog

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
                    db.execSQL("delete from reminders where Outfit_ID = ?", arrayOf(cat.ID))
                    db.execSQL("delete from consists_of where Outfit_ID = ?", arrayOf(cat.ID))
                    Toast.makeText(con,"Outfit deleted", Toast.LENGTH_SHORT).show()
                }
                builder.setNegativeButton("No"){
                        dialog, which ->
                    dialog.dismiss()
                }
                builder.show()

            true
        }


        p0.imageviewname.setOnClickListener {

            val itemview: ImageView
            val itemoccasion: TextView
            val itemweather: TextView
            val lastworn: TextView
            val timesworn: TextView
            val txtclose: TextView


            myDialog = Dialog(con)

            myDialog.setContentView(R.layout.outfitpopup)
            txtclose = myDialog.findViewById(R.id.outfittxtclose)
            txtclose.text = "X"

            itemview = myDialog.findViewById(R.id.outfitview)
            itemoccasion = myDialog.findViewById(R.id.outfitoccasion)
            itemweather = myDialog.findViewById(R.id.outfitweather)
            lastworn = myDialog.findViewById(R.id.outfitlastworn)
            timesworn = myDialog.findViewById(R.id.outfittimesworn)

            myDialog.getWindow().setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            myDialog.show()

            txtclose.setOnClickListener {
                myDialog.dismiss()
            }
            val obj2 = icloset(con)
            val db2 = obj2.readableDatabase

            try {

                val storageDirectory = Environment.getExternalStorageDirectory().toString()

                val file = File(storageDirectory, cat.thumbnail)
                itemview.setImageURI(Uri.parse(file.absolutePath))
            } catch (e: Exception) {
                e.printStackTrace()
            }

            val occasioncur = db2.rawQuery("select Occasion from outfit_occasion where Outfit_ID = ?", arrayOf(cat.ID))
            occasioncur.moveToFirst()

            val OccasionArray = ArrayList<String>()
            while (!occasioncur.isAfterLast) {
                OccasionArray.add(occasioncur.getString(0))
                occasioncur.moveToNext()
            }
            val str: String = OccasionArray.joinToString()
            itemoccasion.text = str


            val weathercur = db2.rawQuery("select Weather from outfit_weather where Outfit_ID = ?", arrayOf(cat.ID))
            weathercur.moveToFirst()

            val weatherArray = ArrayList<String>()
            while (!weathercur.isAfterLast) {
                weatherArray.add(weathercur.getString(0))
                weathercur.moveToNext()
            }
            val str2: String = weatherArray.joinToString()
            itemweather.text = str2

            val cur2 = db2.rawQuery("select Times_worn,Last_time_worn from outfit where Outfit_ID = ?", arrayOf(cat.ID))
            cur2.moveToFirst()

            val times_worn = cur2.getString(0)
            val last_time_worn = cur2.getString(1)


            timesworn.text = times_worn.toString()
            lastworn.text = last_time_worn.toString()


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