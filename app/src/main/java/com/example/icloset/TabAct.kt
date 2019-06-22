package com.example.icloset

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import kotlinx.android.synthetic.main.activity_tab.*

class TabAct : AppCompatActivity(), TabLayout.OnTabSelectedListener {
    override fun onTabReselected(p0: TabLayout.Tab?) {

    }

    override fun onTabUnselected(p0: TabLayout.Tab?) {

    }

    override fun onTabSelected(tab: TabLayout.Tab) {

       // tv.text = tab.text
        //iv.setImageDrawable(tab.icon)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab)

        var courses= arrayOf("Closet","Home","Calendar")
        var icons = arrayOf(R.drawable.clothes,R.drawable.home,R.drawable.calendar)

        for(x in 0..courses.size-1)
        {
            tab2.addTab(tab2.newTab().setText(courses[x]).setIcon(icons[x]))
        }

        tab2.addOnTabSelectedListener(this)
    }
}











