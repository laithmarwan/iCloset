package com.example.icloset

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import com.example.icloset.AppInfo.Companion.wait
import kotlinx.android.synthetic.main.activity_theme_selector.*

class Theme_selector : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
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


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theme_selector)

        rb_default.setOnClickListener {
            AppInfo.theme = 0
            startActivity(Intent(this,Theme_selector::class.java))
            finish()
        }

        rb_theme1.setOnClickListener {
            AppInfo.theme = 1
            startActivity(Intent(this,Theme_selector::class.java))
            finish()
        }

        rb_theme2.setOnClickListener {
            AppInfo.theme = 2
            startActivity(Intent(this,Theme_selector::class.java))
            finish()
        }

        rb_theme3.setOnClickListener {
            AppInfo.theme = 3
            startActivity(Intent(this,Theme_selector::class.java))
            finish()
        }

        button3.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }


    }


}
