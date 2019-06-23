package com.example.icloset

import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.nav_header_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        //our code from here


        var adp = FPA(supportFragmentManager)
        vp.adapter = adp

        vp.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))

        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(vp))

        var navigationView =  findViewById(R.id.nav_view) as NavigationView
        var headerView: View = navigationView.getHeaderView(0)
        var navUsername:TextView  =  headerView.findViewById(R.id.tv_name) as (TextView)
        navUsername.setText(AppInfo.Name)
        var navEmail:TextView  =  headerView.findViewById(R.id.tv_email) as (TextView)
        navEmail.setText(AppInfo.Email)




        var obj1 = icloset(this)
        var db = obj1.writableDatabase
        var cur= db.rawQuery("select * from item", arrayOf())
        if(cur.count == 0 && intent.getStringExtra("act") == "login"){

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Lets start")
            builder.setMessage("Would you like to add your closet?")
            builder.setPositiveButton("Start"
            ) { dialogInterface: DialogInterface, i: Int ->
                vp.currentItem = 0
            }

            builder.setNegativeButton("Later"){
                    dialog, which ->
                dialog.dismiss()}
            builder.show()
        }


        /* main_nav.menu.getItem(1).setChecked(true)
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

            true
        }*/




        //till here

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        vp.currentItem = 1
        nav_view.setNavigationItemSelectedListener(this)
    }
    class FPA(fm: FragmentManager) : FragmentPagerAdapter(fm)
    {
        override fun getItem(position: Int): Fragment {

            return when (position) {
                0 -> ClosetFragment()
                1 -> HomeFragment()
                else -> CalendarFragment()
            }

        }

        override fun getCount(): Int {
            return 3
        }

    }
    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_account -> {
                Toast.makeText(this,AppInfo.UserID+AppInfo.Gender+AppInfo.Name+AppInfo.Address, Toast.LENGTH_SHORT).show()
            }
            R.id.nav_theme -> {

            }
            R.id.nav_backup -> {

            }
            R.id.nav_contact -> {

            }
            R.id.nav_help -> {
                PrefManager(this).clearPreference()
            }
            R.id.nav_logout -> {
                val builder = android.app.AlertDialog.Builder(this)
                builder.setTitle("Alert")
                builder.setMessage("Are you sure?")
                builder.setPositiveButton("Yes"){dialog, which ->
                    AppInfo.UserID=""
                    AppInfo.Name=""
                    AppInfo.Email=""
                    AppInfo.Address=""
                    var sp= this.getSharedPreferences("user_data",
                        Context.MODE_PRIVATE)
                    var editor = sp.edit()
                    editor.putString("user_id","")
                    editor.commit()
                    var i = Intent(this, LoginAct::class.java)
                    startActivity(i)
                    finish()
                }
                builder.setNegativeButton("No"){
                        dialog, which ->
                    dialog.dismiss()
                }
                builder.show()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
