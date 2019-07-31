package com.example.icloset

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Environment
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.io.File
import java.lang.Exception


class CustomAdapter(private val catList :ArrayList<Categories>, private val con:Activity) : RecyclerView.Adapter<CustomAdapter.ViewHolder>(){

    private lateinit var myDialog:Dialog

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
            val txtclose: TextView
            val itemview:ImageView
            val view0: View
            val view1: View
            val view2: View
            val view3: View
            val view4: View
            val view5: View
            val itemoccasion: TextView
            val itemweather: TextView
            val lastworn: TextView
            val timesworn: TextView
            val itemtype: TextView
            val itemdesc: TextView

            myDialog = Dialog(con)
            myDialog.setContentView(R.layout.custompopup)
            txtclose = myDialog.findViewById(R.id.txtclose)
            itemview = myDialog.findViewById(R.id.itemview)
            view0 = myDialog.findViewById(R.id.view0)
            view1 = myDialog.findViewById(R.id.view1)
            view2 = myDialog.findViewById(R.id.view2)
            view3 = myDialog.findViewById(R.id.view3)
            view4 = myDialog.findViewById(R.id.view4)
            view5 = myDialog.findViewById(R.id.view5)
            itemoccasion = myDialog.findViewById(R.id.itemoccasion)
            itemweather = myDialog.findViewById(R.id.itemweather)
            lastworn = myDialog.findViewById(R.id.lastworn)
            timesworn = myDialog.findViewById(R.id. timesworn)
            itemtype = myDialog.findViewById(R.id.itemtype)
            itemdesc = myDialog.findViewById(R.id.itemdesc)

            txtclose.text = "X"


            if(AppInfo.act == "outfit")
            {
                AppInfo.itemID = cat.ID
                con.setResult(RESULT_OK)
                con.finish()
            }
            else if(AppInfo.act == "closet"){
                myDialog.window.setBackgroundDrawable(ColorDrawable(Color.WHITE))
                myDialog.show()

                txtclose.setOnClickListener {
                    myDialog.dismiss()
                }

                val obj = icloset(con)
                val db = obj.readableDatabase
                val colorcur = db.rawQuery("select Red,Green,Blue from color c,contains n where c.Color_ID = n.Color_ID and n.Item_ID = ?", arrayOf(cat.ID))
                colorcur.moveToFirst()

                var r = arrayOf(-1,-1,-1,-1,-1,-1)
                var g = arrayOf(-1,-1,-1,-1,-1,-1)
                var b = arrayOf(-1,-1,-1,-1,-1,-1)
                var i = 0
                while (!colorcur.isAfterLast){
                    r[i]=(colorcur.getInt(0))
                    g[i]=(colorcur.getInt(1))
                    b[i]=(colorcur.getInt(2))
                    colorcur.moveToNext()
                    i++
                }

                if(r[0] != -1 && g[0] != -1 && b[0] != -1){
                    view0.setBackgroundColor(Color.rgb(r[0],g[0],b[0]))
                }
                if(r[1] != -1 && g[1] != -1 && b[1] != -1){
                    view1.setBackgroundColor(Color.rgb(r[1],g[1],b[1]))
                }
                if(r[2] != -1 && g[2] != -1 && b[2] != -1){
                    view2.setBackgroundColor(Color.rgb(r[2],g[2],b[2]))
                }
                if(r[3] != -1 && g[3] != -1 && b[3] != -1){
                    view3.setBackgroundColor(Color.rgb(r[3],g[3],b[3]))
                }
                if(r[4] != -1 && g[4] != -1 && b[4] != -1){
                    view4.setBackgroundColor(Color.rgb(r[4],g[4],b[4]))
                }
                if(r[5] != -1 && g[5] != -1 && b[5] != -1){
                    view5.setBackgroundColor(Color.rgb(r[5],g[5],b[5]))
                }

                try{

                    val storageDirectory = Environment.getExternalStorageDirectory().toString()

                    val file = File(storageDirectory,cat.thumbnail)
                    itemview.setImageURI(Uri.parse(file.absolutePath))}
                catch (e: Exception)
                {
                    e.printStackTrace()
                }

                val occasioncur = db.rawQuery("select Occasion from item_occasion where Item_ID = ?", arrayOf(cat.ID))
                occasioncur.moveToFirst()
                
                val OccasionArray = ArrayList<String>()
                while (!occasioncur.isAfterLast){
                    OccasionArray.add(occasioncur.getString(0))
                    occasioncur.moveToNext()
                }
                val str:String = OccasionArray.joinToString()
                itemoccasion.text = str.toString()


                val weathercur = db.rawQuery("select Weather from item_weather where Item_ID = ?", arrayOf(cat.ID))
                weathercur.moveToFirst()
                
                val weatherArray = ArrayList<String>()
                while (!weathercur.isAfterLast){
                    weatherArray.add(weathercur.getString(0))
                    weathercur.moveToNext()
                }
                val str2:String = weatherArray.joinToString()
                itemweather.text = str2.toString()

                val cur = db.rawQuery("select Times_worn,Last_time_worn from item where Item_ID = ?", arrayOf(cat.ID))
                cur.moveToFirst()

                val times_worn = cur.getString(0)
                val last_time_worn = cur.getString(1)
                val type = cat.type
                val description = cat.desc

                timesworn.text = times_worn.toString()
                lastworn.text = last_time_worn.toString()
                itemtype.text = type
                itemdesc.text = description


                val testcur = db.rawQuery("select Red,Green,Blue,ClassR,ClassG,ClassB from color c,contains n where c.Color_ID = n.Color_ID and n.Item_ID = ?",
                    arrayOf(cat.ID))

                if(testcur.count!=0 ){

                    testcur.moveToFirst()

                    val str = "rgb:"+testcur.getString(0)+ "-"+testcur.getString(1)+"-"+testcur.getString(2)
                    Toast.makeText(con,str +" class:"+testcur.getString(3)+"/"+testcur.getString(4)+"/"+testcur.getString(5),Toast.LENGTH_LONG).show()


                }}
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
        }
    }


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
