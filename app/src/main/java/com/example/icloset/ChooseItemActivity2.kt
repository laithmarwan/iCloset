package com.example.icloset

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_choose_item2.*

class ChooseItemActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_item2)

        var cats = ArrayList<Categories>()
        var obj = icloset(this)
        var db = obj.readableDatabase
        var cur = db.rawQuery("select * from item where Type=?", arrayOf(AppInfo.type))
        if(cur.count ==0){
            Toast.makeText(this,"No items in this category", Toast.LENGTH_SHORT).show()
        }
        else{
            cur.moveToFirst()
            while (!cur.isAfterLast){


                cats.add(Categories(cur.getString(cur.getColumnIndex("Item_ID")),
                    cur.getString(cur.getColumnIndex("Type")),
                    cur.getString(cur.getColumnIndex("Description")),
                    cur.getString(cur.getColumnIndex("Item_image"))))


                cur.moveToNext()
            }
        }
        AppInfo.act = "outfit"
        recView.layoutManager = GridLayoutManager(this,3)
        val adapter = CustomAdapter(cats, this)
        recView.adapter = adapter





    }
}
