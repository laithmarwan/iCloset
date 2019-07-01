package com.example.icloset

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_choose_item.*

class ChooseItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_item)
        var arr = ArrayList<String>()
        arr.add("Tops")
        arr.add("Bottoms")
        arr.add("Shoes")
        if(AppInfo.Gender == "0")
            arr.add("Dresses")
        arr.add("Bags")
        arr.add("Accessories")
        arr.add("Outerwear")
        var adp = ArrayAdapter(this,R.layout.layout_group,arr)
        lv.adapter = adp

        lv.setOnItemClickListener { parent, view, position, id ->
            val intent:Intent= intent
            when (position) {
                0 -> {
                    intent.putExtra("Type","Tops")

                }
                1 -> {
                    intent.putExtra("Type","Bottoms")

                }
                2 -> {
                    intent.putExtra("Type","Shoes")

                }
                3 -> {
                    if(AppInfo.Gender == "0")
                        intent.putExtra("Type","Dresses")
                    else
                        intent.putExtra("Type","Bags")

                }
                4 -> {
                    if(AppInfo.Gender == "0")
                        intent.putExtra("Type","Bags")
                    else
                        intent.putExtra("Type","Accessories")

                }
                5 -> {
                    if(AppInfo.Gender == "0")
                        intent.putExtra("Type","Accessories")
                    else
                        intent.putExtra("Type","Outerwear")

                }
                else -> {
                    intent.putExtra("Type","Outerwear")

                }


        }

            setResult(Activity.RESULT_OK,intent)
            finish()



        }

    }


}
