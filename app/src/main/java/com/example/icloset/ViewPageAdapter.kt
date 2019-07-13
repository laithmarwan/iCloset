package com.example.icloset

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import java.io.File
import java.lang.Exception

class ViewPageAdapter(private val context: Context,private val mArray:ArrayList<Outfit>) : PagerAdapter(){
    private var layoutInflater:LayoutInflater?= null



    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 === `p1`
    }

    override fun getCount(): Int {
        return mArray.size

    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = layoutInflater!!.inflate(R.layout.custom_layout, null)
        val cat:Outfit = mArray[position]
        try{

            val storageDirectory = Environment.getExternalStorageDirectory().toString()

            val file = File(storageDirectory,cat.thumbnail)
            val image = v.findViewById<View>(R.id.image_view8) as ImageView
            image.setImageURI(Uri.parse(file.absolutePath))
            }
        catch (e: Exception)
        {
            e.printStackTrace()
        }


        val vp = container as ViewPager
        vp.addView(v, 0)
        return v

    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val v = `object` as View
        vp.removeView(v)
    }
}
