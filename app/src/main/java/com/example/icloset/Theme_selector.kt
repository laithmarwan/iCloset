package com.example.icloset

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_theme_selector.*

class Theme_selector : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        if(AppInfo.theme == 0)
        setTheme(R.style.AppTheme)
    else{
        setTheme(R.style.BlackTheme)
    }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theme_selector)

        btn_theme.setOnClickListener {

            AppInfo.theme = 1
            startActivity(Intent(this,Theme_selector::class.java))
            finish()
        }




    }


}
