package com.example.icloset


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.AnimationDrawable
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.util.Range
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.theartofdev.edmodo.cropper.CropImage
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fragment_closet.*
import kotlinx.android.synthetic.main.fragment_new_outfit.*
import kotlinx.android.synthetic.main.nav_header_main.*
import java.io.File
import java.lang.Exception


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        if(AppInfo.theme == 0){
            setTheme(R.style.AppTheme)
        }
        if(AppInfo.theme == 1){
            setTheme(R.style.AppTheme1)
        }
        if(AppInfo.theme == 2){
            setTheme(R.style.AppTheme2)
        }

        if(AppInfo.theme == 3){
            setTheme(R.style.AppTheme3)
        }

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
        if(intent.getStringExtra("act") == "login") {
            if (Build.VERSION.SDK_INT > 22) {
                requestPermissions(arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,
                    android.Manifest.permission.ACCESS_FINE_LOCATION),2)

            }
            if (cur.count == 0) {

                val builder = AlertDialog.Builder(this)
                builder.setTitle("Lets start")
                builder.setMessage("Would you like to add your closet?")
                builder.setPositiveButton(
                    "Start"
                ) { dialogInterface: DialogInterface, i: Int ->
                    var trans = supportFragmentManager.beginTransaction()
                    var obj = ClosetFragment()
                    trans.replace(R.id.main_frame, obj)
                    trans.commit()
                    main_nav.menu.getItem(1).isChecked = true
                }

                builder.setNegativeButton("Later") { dialog, which ->
                    dialog.dismiss()
                }
                builder.show()
            }

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
                startActivity(Intent(this,Theme_selector::class.java))

            }
            R.id.nav_backup -> {
                Snackbar.make(this.findViewById(R.id.main_frame) , "Backup in progress...", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

            }
            R.id.nav_contact -> {
                //var trans = supportFragmentManager.beginTransaction().replace(R.id.main_frame, ContactUs())
                //trans.addToBackStack(null)
                //trans.commit()
                var recipient = "support@icloset.com"
                var subject = "FeedBack"
                var message = ""

                sendEmail(recipient, subject, message)
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

    private fun sendEmail(recipient: String, subject: String, message: String) {
        val mIntent = Intent(Intent.ACTION_SEND)
        mIntent.data = Uri.parse("")
        mIntent.type = "text/plain"

        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
        mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        mIntent.putExtra(Intent.EXTRA_TEXT, message)

        try {
            startActivity(Intent.createChooser(mIntent, ""))
        }

        catch (e: Exception){
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {


            var result = CropImage.getActivityResult(data)
            var i = Intent(this, PhotoEditReview::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            AppInfo.img = result.uri
            startActivity(i)

        }
    }

}
