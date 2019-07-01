package com.example.icloset

import android.content.DialogInterface
import android.content.Intent
import android.content.res.Resources
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.support.v4.app.DialogFragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.graphics.Palette
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_photo_edit_review.*
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.lang.Exception
import java.lang.Thread.sleep
import java.util.*

class PhotoEditReview : AppCompatActivity() {

    private lateinit var bitmap: Bitmap


    private var vibrantSwatch: Palette.Swatch? = null
    private var lightVibrantSwatch: Palette.Swatch? = null
    private var darkVibrantSwatch: Palette.Swatch? = null
    private var mutedSwatch: Palette.Swatch? = null
    private var lightMutedSwatch: Palette.Swatch? = null
    private var darkMutedSwatch: Palette.Swatch? = null
    private var swatchNumber: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_edit_review)
        item_photo_editor.setImageURI(AppInfo.img)

        item_photo_editor.setOnClickListener {
            findColors() }



        item_photo_color_info.setOnClickListener {
                //item_photo_editor.setImageResource(R.drawable.home)
                item_photo_editor.isDrawingCacheEnabled = true
                item_photo_editor.buildDrawingCache(true)

                item_photo_editor.setOnTouchListener { v, event ->
                    if (event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_MOVE){
                        bitmap = item_photo_editor.drawingCache
                        val pixel = bitmap.getPixel(event.x.toInt(), event.y.toInt())

                        val r = Color.red(pixel)
                        val g = Color.green(pixel)
                        val b = Color.blue(pixel)

                        val hex = "#" + Integer.toHexString(pixel)
                        item_photo_color_info.setBackgroundColor(Color.rgb(r,g,b))
                        //item_photo_hex_info.text = "RGB: $r, $g, $b \n HEX: $hex"
                    }
                    true
                }
        }

        item_photo_color_info2.setOnClickListener {
            item_photo_editor.isDrawingCacheEnabled = true
            item_photo_editor.buildDrawingCache(true)

            item_photo_editor.setOnTouchListener { v, event ->
                if (event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_MOVE){
                    bitmap = item_photo_editor.drawingCache
                    val pixel = bitmap.getPixel(event.x.toInt(), event.y.toInt())

                    val r = Color.red(pixel)
                    val g = Color.green(pixel)
                    val b = Color.blue(pixel)

                    val hex = "#" + Integer.toHexString(pixel)
                    item_photo_color_info2.setBackgroundColor(Color.rgb(r,g,b))
                    //item_photo_hex_info.text = "RGB: $r, $g, $b \n HEX: $hex"
                }
                true
            }
        }

        item_photo_color_info3.setOnClickListener {
            item_photo_editor.isDrawingCacheEnabled = true
            item_photo_editor.buildDrawingCache(true)

            item_photo_editor.setOnTouchListener { v, event ->
                if (event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_MOVE){
                    bitmap = item_photo_editor.drawingCache
                    val pixel = bitmap.getPixel(event.x.toInt(), event.y.toInt())

                    val r = Color.red(pixel)
                    val g = Color.green(pixel)
                    val b = Color.blue(pixel)

                    val hex = "#" + Integer.toHexString(pixel)
                    item_photo_color_info3.setBackgroundColor(Color.rgb(r,g,b))
                    //item_photo_hex_info.text = "RGB: $r, $g, $b \n HEX: $hex"
                }
                true
            }
        }

        item_photo_color_info4.setOnClickListener {
            item_photo_editor.isDrawingCacheEnabled = true
            item_photo_editor.buildDrawingCache(true)

            item_photo_editor.setOnTouchListener { v, event ->
                if (event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_MOVE){
                    bitmap = item_photo_editor.drawingCache
                    val pixel = bitmap.getPixel(event.x.toInt(), event.y.toInt())

                    val r = Color.red(pixel)
                    val g = Color.green(pixel)
                    val b = Color.blue(pixel)

                    val hex = "#" + Integer.toHexString(pixel)
                    item_photo_color_info4.setBackgroundColor(Color.rgb(r,g,b))
                    //item_photo_hex_info.text = "RGB: $r, $g, $b \n HEX: $hex"
                }
                true
            }
        }

        item_photo_color_info5.setOnClickListener {
            item_photo_editor.isDrawingCacheEnabled = true
            item_photo_editor.buildDrawingCache(true)

            item_photo_editor.setOnTouchListener { v, event ->
                if (event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_MOVE){
                    bitmap = item_photo_editor.drawingCache
                    val pixel = bitmap.getPixel(event.x.toInt(), event.y.toInt())

                    val r = Color.red(pixel)
                    val g = Color.green(pixel)
                    val b = Color.blue(pixel)

                    val hex = "#" + Integer.toHexString(pixel)
                    item_photo_color_info5.setBackgroundColor(Color.rgb(r,g,b))
                    //item_photo_hex_info.text = "RGB: $r, $g, $b \n HEX: $hex"
                }
                true
            }
        }

        item_photo_color_info6.setOnClickListener {
            item_photo_editor.isDrawingCacheEnabled = true
            item_photo_editor.buildDrawingCache(true)

            item_photo_editor.setOnTouchListener { v, event ->
                if (event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_MOVE){
                    bitmap = item_photo_editor.drawingCache
                    val pixel = bitmap.getPixel(event.x.toInt(), event.y.toInt())

                    val r = Color.red(pixel)
                    val g = Color.green(pixel)
                    val b = Color.blue(pixel)

                    val hex = "#" + Integer.toHexString(pixel)
                    item_photo_color_info6.setBackgroundColor(Color.rgb(r,g,b))
                    //item_photo_hex_info.text = "RGB: $r, $g, $b \n HEX: $hex"
                }
                true
            }
        }


        ok_btn.setOnClickListener {

            if(AppInfo.act == "add"){
                //code for adding item to database
                if(season_list.text.toString() == "Select one season or more" || season_view.text.toString() == ""
                    || occasion_list.text.toString() == "Select one or more occasions" || occasion_view.text.toString() == ""
                    || category_list.text.toString() == "Select only one category" || category_view.text.toString() == "" ){
                    Toast.makeText(this,"Make sure to fill all info",Toast.LENGTH_SHORT).show()
                }
                else{
                        AppInfo.img_url = 1
                    var obj = icloset(this)
                    var db = obj.writableDatabase
                    var typedesc =  category_view.text.toString().split(" - ")
                    var cur = db.rawQuery("select Item_ID from item", arrayOf())
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
                    var image_url = "item_"+AppInfo.img_url+".jpg"
                    this.saveImageToStorage(image_url)
                    var seasonarr = season_view.text.toString().split(", ")
                    var occasionarr = occasion_view.text.toString().split(", ")
                    db.execSQL("insert into item (Type,Description,Times_worn,Available,Item_image) " +
                            "values (?,?,0,1,?)", arrayOf(typedesc[0],typedesc[1],image_url))
                    var cur2 = db.rawQuery("select Item_ID from item where Item_image=?", arrayOf(image_url))
                    if(cur2.count != 0){
                        cur2.moveToFirst()
                        AppInfo.img_url = cur2.getString(0).toInt()
                    }
                    for(i in 0 until seasonarr.size)
                        db.execSQL("insert into item_weather values (?,?)", arrayOf(AppInfo.img_url,seasonarr[i]))

                    for(i in 0 until occasionarr.size)
                        db.execSQL("insert into item_occasion values (?,?)", arrayOf(AppInfo.img_url,occasionarr[i]))


                    Toast.makeText(this,"Item added successfully",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,MainActivity::class.java))
                    finish()

                }
            }
            else{
                //code for help me match
                Toast.makeText(this,"Matching item...",Toast.LENGTH_SHORT).show()
            }


        }


        season_list.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val seasonArray = arrayOf("Winter","Spring","Summer","Autumn")
            var checkedArray = booleanArrayOf(false,false,false,false)
            val seasonlist = Arrays.asList(*seasonArray)
            builder.setTitle("Select Seasons")
            builder.setMultiChoiceItems(seasonArray,checkedArray){dialog, which, isChecked ->
                checkedArray[which] = isChecked
                val currentItem = seasonlist[which]
            }
            builder.setPositiveButton("OK"){dialog, which ->
                season_view.setText("")
                for (i in checkedArray.indices){
                    val checked = checkedArray[i]
                    if (checked){
                        if(season_view.text.toString() == "")
                            season_view.setText(seasonlist[i])
                        else
                            season_view.setText(season_view.text.toString() + ", " + seasonlist[i])
                        // database
                    }

                }
            }
            builder.setNegativeButton("Cancel"){dialog, which ->
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }

       occasion_list.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val occasionArray = arrayOf("Work","School","Friends","Wedding","Restaurant","Party","Gym","Trip","Other")
            var checkedArray = booleanArrayOf(false,false,false,false,false,false,false,false,false)
            val occasionlist = Arrays.asList(*occasionArray)
            builder.setTitle("Select Occasions")
            builder.setMultiChoiceItems(occasionArray,checkedArray){dialog, which, isChecked ->
                checkedArray[which] = isChecked
                val currentItem = occasionlist[which]
            }
            builder.setPositiveButton("OK"){dialog, which ->
                occasion_view.setText("")
                for (i in checkedArray.indices){
                    val checked = checkedArray[i]
                    if (checked){
                        if(occasion_view.text.toString()=="")
                            occasion_view.setText(occasionlist[i])
                        else{
                            occasion_view.setText(occasion_view.text.toString() + ", " +occasionlist[i])
                        }
                        // database
                    }

                }
            }
            builder.setNegativeButton("Cancel"){dialog, which ->
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }

        category_list.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val categoryArray = arrayOf(
                    "Tops - Blazers",
                    "Tops - Shirts",
                    "Tops - Sweaters",
                    "Tops - T-shirts",
                    "Tops - Sleeveless",
                    "Bottoms - Trousers",
                    "Bottoms - Shorts",
                    "Bottoms - Jeans",
                    "Bottoms - Skirts",
                    "Shoes - Boots",
                    "Shoes - Flats",
                    "Shoes - Heels",
                    "Shoes - Sandals",
                    "Shoes - Boat Shoes",
                    "Shoes - Sneakers",
                    "Shoes - Trainers",
                    "Dresses - Evening Gowns",
                    "Dresses - Cocktail Dress",
                    "Dresses - Strapless Dress",
                    "Dresses - Sundress",
                    "Dresses - Shirt Dress",
                    "Bags - Satchels",
                    "Bags - Totes",
                    "Bags - Clutches",
                    "Bags - Briefcase",
                    "Bags - Messenger Bag",
                    "Bags - Hobo",
                    "Accessories - Watches",
                    "Accessories - Sunglasses",
                    "Accessories - Belts",
                    "Accessories - Hats",
                    "Accessories - Necklaces",
                    "Accessories - Bracelets",
                    "Accessories - Rings",
                    "Accessories - Headbands",
                    "Accessories - Earrings",
                    "Accessories - Scarves",
                    "Outerwear - Jackets",
                    "Outerwear - Coats"
                )

            builder.setTitle("Select Category")
            builder.setSingleChoiceItems(categoryArray, -1){dialog: DialogInterface, which: Int ->
                category_view.setText(categoryArray[which])
            }
            builder.setPositiveButton("OK"){dialog, which ->

            }
            builder.setNegativeButton("Cancel"){dialog, which ->
                category_view.setText("")
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }


    }





    private fun saveImageToStorage(url:String): Boolean {
        val externalStorageState = Environment.getExternalStorageState()
        if(externalStorageState == Environment.MEDIA_MOUNTED){
            val storageDirectory = Environment.getExternalStorageDirectory().toString()

            val file = File(storageDirectory,url)
            try {
                val stream:OutputStream = FileOutputStream(file)
                val bm = (item_photo_editor.drawable as BitmapDrawable).bitmap
                val resized = Bitmap.createScaledBitmap(bm, 300, 400, true)
                resized.compress(Bitmap.CompressFormat.JPEG,100,stream)
                stream.flush()
                stream.close()
                Toast.makeText(this,"Stored successfully ${Uri.parse(file.absolutePath)}",Toast.LENGTH_SHORT).show()
                return true
           }catch (e:Exception){
                e.printStackTrace()
                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
                return false
           }
        }else{
            Toast.makeText(this,"Unable to save media to storage",Toast.LENGTH_SHORT).show()
            return false
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //if (requestCode === 123){
            //camera action here
            //var bmp = data?.extras?.get("data") as Bitmap
            //item_photo_editor.setImageBitmap(bmp)
            // crop


       // }

       /* if (resultCode == Activity.RESULT_OK && requestCode == 1234){
            item_photo_editor.setImageURI(data?.data)
        }*/

}

    private fun findColors(){

        val bmp = (item_photo_editor.drawable as BitmapDrawable).bitmap


        Palette.from(bmp).maximumColorCount(32).generate { palette ->
            vibrantSwatch = palette!!.vibrantSwatch
            lightVibrantSwatch = palette.lightVibrantSwatch
            darkVibrantSwatch = palette.darkVibrantSwatch
            mutedSwatch = palette.mutedSwatch
            lightMutedSwatch = palette.lightMutedSwatch
            darkMutedSwatch = palette.darkMutedSwatch


        }


        var currentSwatch: Palette.Swatch? = null


        currentSwatch = vibrantSwatch
        if (currentSwatch != null) {
            item_photo_color_info.setBackgroundColor(currentSwatch.rgb)

        }

        //textViewTitle.setText("Vibrant")

        currentSwatch = lightVibrantSwatch
        if (currentSwatch != null) {
            item_photo_color_info2.setBackgroundColor(currentSwatch.rgb)

        }


        //textViewTitle.setText("Light Vibrant")

        currentSwatch = darkVibrantSwatch
        if (currentSwatch != null) {
            item_photo_color_info3.setBackgroundColor(currentSwatch.rgb)

        }


        //textViewTitle.setText("Dark Vibrant")


        currentSwatch = mutedSwatch
        if (currentSwatch != null) {
            item_photo_color_info4.setBackgroundColor(currentSwatch.rgb)

        }

        //textViewTitle.setText("Muted")


        currentSwatch = lightMutedSwatch
        if (currentSwatch != null) {
            item_photo_color_info5.setBackgroundColor(currentSwatch.rgb)

        }


        //textViewTitle.setText("Light Muted")

        currentSwatch = darkMutedSwatch
        if (currentSwatch != null) {
            item_photo_color_info6.setBackgroundColor(currentSwatch.rgb)

        }

        //textViewTitle.setText("Dark Muted")




//textViewTitle.setTextColor(currentSwatch.titleTextColor)
//textViewBody.setTextColor(currentSwatch.bodyTextColor)





    }

    private fun openCamera(){
        //var i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

         var crm = CropImage.activity()
            .setGuidelines(CropImageView.Guidelines.ON)

             crm.start(this)

    }

   //private fun pickImageFromGallery() {
        //val intent = Intent(Intent.ACTION_PICK)
        //intent.type = "image/*"
        //startActivityForResult(intent, 1234)
   // }



}
