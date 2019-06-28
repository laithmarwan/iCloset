package com.example.icloset


import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Range
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fragment_closet.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)



        //our code from here



        var navigationView = findViewById<NavigationView>(R.id.nav_view)
        var headerView: View = navigationView.getHeaderView(0)
        var navUsername:TextView  =  headerView.findViewById(R.id.tv_name) as (TextView)
        navUsername.text = AppInfo.Name
        var navEmail:TextView  =  headerView.findViewById(R.id.tv_email) as (TextView)
        navEmail.text = AppInfo.Email




        var obj1 = icloset(this)
        var db = obj1.writableDatabase
        var cur= db.rawQuery("select * from item", arrayOf())
        if(cur.count == 0 && intent.getStringExtra("act") == "login"){

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Lets start")
            builder.setMessage("Would you like to add your closet?")
            builder.setPositiveButton("Start"
            ) { dialogInterface: DialogInterface, i: Int ->
                var trans = supportFragmentManager.beginTransaction()
                var obj = ClosetFragment()
                trans.replace(R.id.main_frame,obj)
                trans.commit()
                main_nav.menu.getItem(1).isChecked = true
            }

            builder.setNegativeButton("Later"){
                    dialog, which ->
                dialog.dismiss()}
            builder.show()
        }


        var trans = supportFragmentManager.beginTransaction()
        var obj = HomeFragment()
        trans.replace(R.id.main_frame,obj)
        trans.addToBackStack(null)
        trans.commit()
        main_nav.menu.getItem(0).isChecked = true



        main_nav.setOnNavigationItemSelectedListener {

                menuItem ->
            var tr = supportFragmentManager.beginTransaction()
            if(menuItem.itemId == R.id.menu_closet){
                var obj = ClosetFragment()
                tr.replace(R.id.main_frame,obj)
                tr.addToBackStack(null)
                tr.commit()

            }
            if(menuItem.itemId == R.id.menu_outfits){
                var obj = OutfitsFragment()
                tr.replace(R.id.main_frame,obj)
                tr.addToBackStack(null)
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
        }




        //till here



        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
    }
    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_account -> {
                var trans = supportFragmentManager.beginTransaction().replace(R.id.main_frame, MyAccount())
                trans.addToBackStack(null)
                trans.commit()

                //Toast.makeText(this,AppInfo.UserID+AppInfo.Gender+AppInfo.Name+AppInfo.Address, Toast.LENGTH_SHORT).show()
            }
            R.id.nav_theme -> {

            }
            R.id.nav_backup -> {
                Snackbar.make(this.findViewById(R.id.main_frame) , "Backup in progress...", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

            }
            R.id.nav_contact -> {
                var trans = supportFragmentManager.beginTransaction().replace(R.id.main_frame, ContactUs())
                trans.addToBackStack(null)
                trans.commit()
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
