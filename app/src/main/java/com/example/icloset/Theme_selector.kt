package com.example.icloset

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_theme_selector.*

class Theme_selector : AppCompatActivity() {



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

        setContentView(R.layout.activity_theme_selector)

        var sp=getSharedPreferences("user_data",
            Context.MODE_PRIVATE)
        var editor = sp.edit()


        rb_default.setOnClickListener {
            AppInfo.theme = 0
            editor.putString("theme","0")
            editor.commit()
            startActivity(Intent(this,Theme_selector::class.java))
            finish()
        }

        rb_theme1.setOnClickListener {
            AppInfo.theme = 1
            editor.putString("theme","1")
            editor.commit()
            startActivity(Intent(this,Theme_selector::class.java))
            finish()
        }

        rb_theme2.setOnClickListener {
            AppInfo.theme = 2
            editor.putString("theme","2")
            editor.commit()
            startActivity(Intent(this,Theme_selector::class.java))
            finish()
        }

        rb_theme3.setOnClickListener {
            AppInfo.theme = 3
            editor.putString("theme","3")
            editor.commit()
            startActivity(Intent(this,Theme_selector::class.java))
            finish()
        }

        btn_set_theme.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,MainActivity::class.java))
        finish()

    }


}
