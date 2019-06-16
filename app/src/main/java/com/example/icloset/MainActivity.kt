package com.example.icloset

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       

    }

    private fun moveToFragment(myFragment: Fragment) {
        var tr = fragmentManager.beginTransaction()
        var obj = myFragment
        tr.replace(R.id.relativeLayout,obj)
        tr.commit()
    }
}
