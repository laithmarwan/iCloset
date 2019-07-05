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
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import java.io.FileOutputStream
import java.io.OutputStream
import java.lang.Exception


class CreateOutfitActivity : AppCompatActivity() {
 private lateinit var bitArray:ArrayList<Bitmap>
    private lateinit var itemArray:ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(AppInfo.theme == 0){
            setTheme(R.style.AppTheme)
        }

        if(AppInfo.theme == 1){
            setTheme(R.style.AppTheme1)
        }
        setContentView(R.layout.activity_create_outfit)
        setSupportActionBar(toolbar)

        bitArray = ArrayList()
        itemArray = ArrayList()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.create_outfit,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {


        if(item?.itemId == R.id.menu_save && itemArray.isNotEmpty()){
           val bm =  createSingleImageFromMultipleImages(bitArray)
            AppInfo.img_url = 1
            val obj = icloset(this)
            val db = obj.writableDatabase
            val cur = db.rawQuery("select Outfit_ID from outfit", arrayOf())
            if(cur.count > 0){
                cur.moveToFirst()
                var max=0
                while(!cur.isAfterLast){
                    if(cur.getString(0).toInt() > max){
                        max = cur.getString(0).toInt()
                    }
                    cur.moveToNext()
                }

                AppInfo.img_url = max+1

            }
            var oldArray = arrayOf(0,0,0,0)
            for (i in 0 until itemArray.size) {
                val cur = db.rawQuery("select Weather from item_weather where Item_ID =?", arrayOf(itemArray[i]))
                cur.moveToFirst()
                val arr1 = arrayOf(0,0,0,0)

                while(!cur.isAfterLast) {
                    val weather = cur.getString(0)
                    if(weather == "Winter")
                        arr1[0]=1
                    if(weather == "Spring")
                        arr1[1]=1
                    if(weather == "Summer")
                        arr1[2]=1
                    if(weather == "Autumn")
                        arr1[3]=1

                    cur.moveToNext()
                }

                if(!oldArray.contentEquals(arrayOf(0,0,0,0))){
                    for (j in 0..3){
                        if(oldArray[j] == 0 && arr1[j] == 1){
                            arr1[j] = 0
                        }
                    }
                }

                    oldArray = arr1

            }


            val image_url = "outfit_"+AppInfo.img_url+".jpg"
            this.saveImageToStorage(image_url,bm)
            db.execSQL("insert into outfit (Times_worn,Available,Outfit_image) " +
                    "values (0,1,?)", arrayOf(image_url))


            var cur2 = db.rawQuery("select Item_ID from item where Item_image=?", arrayOf(image_url))
            if(cur2.count != 0){
                cur2.moveToFirst()
                AppInfo.img_url = cur2.getString(0).toInt()
            }

            val list = ArrayList<String>()
            if(oldArray[0] == 1)
                list.add("Winter")
            if(oldArray[1] == 1)
                list.add("Spring")
            if(oldArray[2] == 1)
                list.add("Summer")
            if(oldArray[3] == 1)
                list.add("Autumn")

            for(i in 0 until list.size)
                db.execSQL("insert into outfit_weather values (?,?)", arrayOf(AppInfo.img_url,list[i]))

            //code for occasions
            val occasion_arr = ArrayList<String>()
            for (i in 0 until itemArray.size) {
                val cur = db.rawQuery("select Occasion from item_occasion where Item_ID =?", arrayOf(itemArray[i]))
                cur.moveToFirst()

                while(!cur.isAfterLast) {
                    val occasion = cur.getString(0)
                    if(!occasion_arr.contains(occasion))
                        occasion_arr.add(occasion)

                    cur.moveToNext()
                }
            }
            for(i in 0 until occasion_arr.size)
                db.execSQL("insert into outfit_occasion values (?,?)", arrayOf(AppInfo.img_url,occasion_arr[i]))

            for(i in 0 until itemArray.size)
                db.execSQL("insert into consists_of values (?,?)", arrayOf(AppInfo.img_url,itemArray[i]))

            Toast.makeText(this,"Item added successfully",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,MainActivity::class.java))
            finish()
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
            val obj = icloset(this)
            val db = obj.readableDatabase
            val cur = db.rawQuery("select * from item where Item_ID =?", arrayOf(AppInfo.itemID))
            if(cur.count ==0){
                Toast.makeText(this,"No items in this category", Toast.LENGTH_SHORT).show()
            }
            else{
                cur.moveToFirst()


                val storageDirectory = Environment.getExternalStorageDirectory().toString()

                val file = File(storageDirectory,cur.getString(cur.getColumnIndex("Item_image")))
                draggableBox.setImageURI(Uri.parse(file.absolutePath))
                val bm = (draggableBox.drawable as BitmapDrawable).bitmap
                bitArray.add(bm)
                itemArray.add(cur.getString(cur.getColumnIndex("Item_ID")))

            }

            root_layout.addView(draggableBox)
    }
    }

    private fun createSingleImageFromMultipleImages(array:ArrayList<Bitmap>): Bitmap {


        val result = Bitmap.createBitmap(array[0].width*array.size, array[0].height*array.size, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(result)
        canvas.drawARGB(255,255,255,255)

        var f = 0f
        for (i in 0 until array.size){
            canvas.drawBitmap(array[i], f, f, null)

            f+=100f

        }
        return result
    }
    private fun saveImageToStorage(url:String, bm:Bitmap): Boolean {
        val externalStorageState = Environment.getExternalStorageState()
        if(externalStorageState == Environment.MEDIA_MOUNTED){
            val storageDirectory = Environment.getExternalStorageDirectory().toString()

            val file = File(storageDirectory,url)
            return try {
                val stream: OutputStream = FileOutputStream(file)
                //val bm = (item_photo_editor.drawable as BitmapDrawable).bitmap
                val resized = Bitmap.createScaledBitmap(bm, 300, 400, true)
                resized.compress(Bitmap.CompressFormat.JPEG,100,stream)
                stream.flush()
                stream.close()
                Toast.makeText(this,"Stored successfully ${Uri.parse(file.absolutePath)}",Toast.LENGTH_SHORT).show()
                true
            }catch (e: Exception){
                e.printStackTrace()
                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
                false
            }
        }else{
            Toast.makeText(this,"Unable to save media to storage",Toast.LENGTH_SHORT).show()
            return false
        }
    }
}
