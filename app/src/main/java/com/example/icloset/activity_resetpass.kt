package com.example.icloset

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_forgotpass.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_resetpass.*

class activity_resetpass : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resetpass)

        btn_submit2.setOnClickListener {

            startActivity(Intent(this, LoginAct::class.java))

        }


    }
}
