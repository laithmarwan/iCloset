package com.example.icloset

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_image_slider.*
import kotlinx.android.synthetic.main.activity_image_slider.view.*

class ImageSlider : AppCompatActivity() {

    private lateinit var viewPager: ViewPager

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_slider)


        //weather API

        val occasion = intent.getStringExtra("occasion")
        val obj = icloset(this)
        val db = obj.readableDatabase
        val mArray:ArrayList<Outfit> = ArrayList()
        val cur = db.rawQuery("select * from outfit o,outfit_occasion oo,outfit_weather ow" +
                " where o.Outfit_ID = oo.Outfit_ID and oo.Outfit_ID = ow.Outfit_ID and ow.Weather = ? and oo.Occasion = ?", arrayOf(AppInfo.season,occasion))
        if(cur.count !=0){
            cur.moveToFirst()

            while(!cur.isAfterLast){
                mArray.add(Outfit(cur.getString(0),cur.getString(2),cur.getInt(5),cur.getString(7)))
                cur.moveToNext()
            }
        }
        else{

            tv_empty.text = "No items found relating to your occasion"
        }

        Toast.makeText(this, AppInfo.season,Toast.LENGTH_LONG).show()

        viewPager = findViewById<View>(R.id.viewPager) as ViewPager

        val adapter = ViewPageAdapter(this,mArray)
        viewPager.adapter = adapter
    }
}
