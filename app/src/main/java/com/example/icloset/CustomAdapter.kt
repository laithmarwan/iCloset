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

class CustomAdapter(private val catList :ArrayList<Categories>, private val con:Activity) : RecyclerView.Adapter<CustomAdapter.ViewHolder>(){
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
            if(AppInfo.act == "outfit")         
            {
                AppInfo.itemID = cat.ID
                con.setResult(RESULT_OK)
                con.finish()
            }
            else if(AppInfo.act == "closet"){
                var weather = ""
                val obj = icloset(con)
                val db = obj.readableDatabase
                val cur = db.rawQuery("select Weather from outfit_weather where Outfit_ID =?", arrayOf(cat.ID))
                cur.moveToFirst()
                while (!cur.isAfterLast){
                    weather += cur.getString(0)
                    cur.moveToNext()
                }

                Toast.makeText(con,"${cat.type}-${cat.desc} | $weather",Toast.LENGTH_LONG).show()

            }

    }
        p0.imageviewname.setOnLongClickListener {
            if(AppInfo.act !== "outfit" && AppInfo.act !== "nodelete"){
                val builder = android.app.AlertDialog.Builder(con)
                builder.setTitle("Delete Item?")
                builder.setMessage("Are you sure?")
                builder.setPositiveButton("Yes"){dialog, which ->
                    catList.removeAt(p1)
                    notifyItemRemoved(p1)
                    val obj = icloset(con)
                    val db = obj.writableDatabase
                    db.execSQL("delete from item where Item_ID = ?", arrayOf(cat.ID))
                    db.execSQL("delete from item_weather where Item_ID = ?", arrayOf(cat.ID))
                    db.execSQL("delete from item_occasion where Item_ID = ?", arrayOf(cat.ID))
                    val cur = db.rawQuery("select Outfit_ID from consists_of where Item_ID =?", arrayOf(cat.ID))
                    if(cur.count !=0){
                    cur.moveToFirst()
                    while(!cur.isAfterLast){
                        db.execSQL("delete from outfit where Outfit_ID = ?", arrayOf(cur.getString(0)))
                        db.execSQL("delete from outfit_weather where Outfit_ID = ?", arrayOf(cur.getString(0)))
                        db.execSQL("delete from outfit_occasion where Outfit_ID = ?", arrayOf(cur.getString(0)))
                        db.execSQL("delete from reminders where Outfit_ID = ?", arrayOf(cur.getString(0)))
                        cur.moveToNext()}
                    }


                    db.execSQL("delete from consists_of where Item_ID = ?", arrayOf(cat.ID))
                    Toast.makeText(con,"Item deleted", Toast.LENGTH_SHORT).show()
                }
                builder.setNegativeButton("No"){
                        dialog, which ->
                    dialog.dismiss()
                }
                builder.show()
            }

            true
        }}


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageviewname = itemView.findViewById(R.id.imageView) as ImageView
        val card = itemView.findViewById(R.id.card) as CardView
    }

/*
    class choiceDrageListener(val context:Context,val imageviewname:ImageView) : View.OnDragListener {

        override fun onDrag(v: View, event: DragEvent): Boolean {

            when (event.action) {
                DragEvent.ACTION_DRAG_STARTED -> {
                }

                DragEvent.ACTION_DRAG_ENTERED -> {
                }

                DragEvent.ACTION_DRAG_EXITED -> {
                }

                DragEvent.ACTION_DROP -> {

                    val view = event.localState as ImageView
                    (v as ImageView).setImageDrawable(context.getResources().getDrawable(R.drawable.plus))
                    imageviewname.setImageDrawable(null)
                }

                DragEvent.ACTION_DRAG_ENDED -> {
                }
            }
            return true
        }
    }
*/
}
