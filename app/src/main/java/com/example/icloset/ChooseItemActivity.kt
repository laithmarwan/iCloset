package com.example.icloset

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_choose_item.*

class ChooseItemActivity : AppCompatActivity() {

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

        setContentView(R.layout.activity_choose_item)

        var trans = supportFragmentManager.beginTransaction()
        trans.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_right,R.anim.enter_from_right,R.anim.exit_to_right)
        trans.replace(R.id.container,ChooseItemFragment())
        trans.commit()

    }


}
