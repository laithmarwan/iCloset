package com.example.icloset

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_create_outfit.*
import java.io.File

class CreateOutfitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(AppInfo.theme == 0){
            setTheme(R.style.AppTheme)
        }

        if(AppInfo.theme == 1){
            setTheme(R.style.AppTheme1)
        }
        setContentView(R.layout.activity_create_outfit)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.create_outfit,menu)


    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {


        if(item?.itemId == R.id.menu_save){

        }
        else if(item?.itemId == R.id.menu_add){
            startActivityForResult(Intent(this,ChooseItemActivity::class.java),1010)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 1010 && resultCode == Activity.RESULT_OK){

//            val str = data?.getStringExtra("Type")

            val draggableBox = DraggableBox(this)

            // Creating a LinearLayout.LayoutParams object for text view
            var params : LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, // This will define text view width
                LinearLayout.LayoutParams.WRAP_CONTENT // This will define text view height
            )

            // Add margin to the text view
            params.setMargins(10,10,10,10)

            // Now, specify the text view width and height (dimension)
            draggableBox.layoutParams = params

            // Display some text on the newly created text view
            var obj = icloset(this)
            var db = obj.readableDatabase
            var cur = db.rawQuery("select * from item where Item_ID =?", arrayOf(AppInfo.itemID))
            if(cur.count ==0){
                Toast.makeText(this,"No items in this category", Toast.LENGTH_SHORT).show()
            }
            else{
                cur.moveToFirst()

                val storageDirectory = Environment.getExternalStorageDirectory().toString()

                val file = File(storageDirectory,cur.getString(cur.getColumnIndex("Item_image")))
                draggableBox.setImageURI(Uri.parse(file.absolutePath))

            }

            root_layout.addView(draggableBox)
    }
    }
}
