package com.example.icloset

import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.app.Fragment
import android.content.DialogInterface
import android.content.Intent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var obj1 = icloset(this)
        var db = obj1.writableDatabase
        var cur= db.rawQuery("select * from item", arrayOf())
        if(cur.count == 0){

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Lets start")
            builder.setMessage("Would you like to add your closet?")
            builder.setPositiveButton("Start",
                { dialogInterface: DialogInterface, i: Int -> var tr = fragmentManager.beginTransaction()
                    var obj = ClosetFragment()
                    tr.replace(R.id.main_frame,obj)
                    tr.commit()
                })

            builder.setNegativeButton("Later"){
                    dialog, which ->
                dialog.dismiss()}
            builder.show()
        }



        main_nav.menu.getItem(2).setChecked(true)
        var trans = fragmentManager.beginTransaction()
        var obj = HomeFragment()
        trans.replace(R.id.main_frame,obj)
        trans.commit()



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