package com.example.icloset

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_choose_item2.*

class ChooseItemActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_item2)


        AppInfo.act = "help"
        recView.layoutManager = GridLayoutManager(this,3)
        val adapter = CustomAdapter(AppInfo.catarr, this)
        recView.adapter = adapter

        btn_ok.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }




    }
}
