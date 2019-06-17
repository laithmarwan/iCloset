package com.example.icloset

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





        main_nav.setOnNavigationItemSelectedListener {

            menuItem ->
            var tr = fragmentManager.beginTransaction()
            if(menuItem.itemId == R.id.menu_closet){
                var obj = ClosetFragment()
                tr.replace(R.id.main_frame,obj)
                tr.commit()
            }
            if(menuItem.itemId == R.id.menu_outfits){
                var obj = OutfitsFragment()
                tr.replace(R.id.main_frame,obj)
                tr.commit()
            }
            if(menuItem.itemId == R.id.menu_home){
                var obj = HomeFragment()
                tr.replace(R.id.main_frame,obj)
                tr.commit()
            }
            if(menuItem.itemId == R.id.menu_calendar){
                var obj = CalendarFragment()
                tr.replace(R.id.main_frame,obj)
                tr.commit()
            }
            if(menuItem.itemId == R.id.menu_settings){
                var obj = SettingsFragment()
                tr.replace(R.id.main_frame,obj)
                tr.commit()
            }
            true
        }

    }


}
