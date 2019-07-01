package com.example.icloset

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_theme_selector.*

class Theme_selector : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (setTheme(getFlag())) R.style.AppThemeDark else R.Style.Apptheme
        setContentView(R.layout.activity_theme_selector)
        btn_theme.setOnClickListener
        run({ saveFlag(!getFlag())
            Intent(startActivity(Theme_selector))
            startActivity()
            finish() })
    }

    private fun saveFlag(flag:Boolean) {
        val preferences = PreferenceManager.getDefaultSharedPreference(this)
        val editor = preferences.edit()
        editor.putBoolean('dark'.flag)
        editor.commit()
    }

}
