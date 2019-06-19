package com.example.icloset

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_settings.*

class IntroAct : AppCompatActivity(), View.OnClickListener {


    lateinit var mPager : ViewPager

    var layouts: IntArray = intArrayOf(R.layout.first_slide,R.layout.second_slide,R.layout.third_slide)

    lateinit var dotsLayout: LinearLayout

    lateinit var dots: Array<ImageView>

    lateinit var mAdapter: PageAdapter

    lateinit var btnNext: Button

    lateinit var btnSkip: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_intro)
        if(PrefManager(this).checkPreference())
        {
            loadHome()
        }
        if (Build.VERSION.SDK_INT >= 19)
        {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        else
        {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }


        mPager = findViewById(R.id.pager) as ViewPager

        mAdapter = PageAdapter(layouts,this)
        mPager.adapter=mAdapter
        dotsLayout= findViewById(R.id.dots) as LinearLayout
        btnSkip = findViewById(R.id.btnSkip)
        btnNext = findViewById(R.id.btnNext)
        btnSkip.setOnClickListener(this)
        btnNext.setOnClickListener(this)
        createDots(0)
        mPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

            }

            override fun onPageSelected(p0: Int) {
                createDots(p0)

                if (p0 == layouts.size -1 )
                {
                    btnNext.setText("start")
                    btnSkip.visibility = View.INVISIBLE
                }
                else
                {
                    btnNext.setText("Next")
                    btnSkip.visibility=View.VISIBLE
                }
            }


        })




       // setContentView(R.layout.activity_main)

    }

    override fun onClick(v: View?) {
        when(v!!.id)
        {
            R.id.btnSkip ->
            {
                loadHome()
                PrefManager(this).writeSP()
            }

            R.id.btnNext ->
            {
                loadNextSlide()
            }
        }

    }

    private fun loadNextSlide() {

        var nextSlide: Int = mPager.currentItem + 1

        if (nextSlide < layouts.size)
        {
            mPager.setCurrentItem(nextSlide)
        }
        else
        {
            loadHome()
            PrefManager(this).writeSP()
        }
    }


    private fun loadHome() {

        startActivity(Intent(this,LoginAct::class.java))
    }

    fun createDots(position:Int)
    {
        if (dotsLayout!=null)
        {
            dotsLayout.removeAllViews()
        }
        dots = Array(layouts.size,{i -> ImageView(this) })

        for (i in 0..layouts.size - 1)
        {
            dots[i] = ImageView(this)
            if (i== position)
            {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this,R.drawable.active_dots))
            }
            else
            {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this,R.drawable.inactive_dots))
            }

            var params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)

            params.setMargins(4,0,4,0)
            dotsLayout.addView(dots[i],params)

        }

    }


}


