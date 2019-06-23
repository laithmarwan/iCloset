package com.example.icloset

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_forgotpass.*
import kotlinx.android.synthetic.main.activity_login.*

class activity_forgotpass : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgotpass)

        btn_submit.setOnClickListener {

            startActivity(Intent(this, activity_resetpass::class.java))
        }

    }
}
