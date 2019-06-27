package com.example.icloset

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.icu.util.Output
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.view.MotionEvent
import android.widget.Toast
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_photo_edit_review.*
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.lang.Exception

class PhotoEditReview : AppCompatActivity() {

    private lateinit var bitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_edit_review)


       // if (AppInfo.act == "cam")
       // {
            openCamera()
       // }

        /*else if(AppInfo.act == "media")
        {
            pickImageFromGallery()
        }*/

        ok_btn.setOnClickListener {
            if(AppInfo.act == "add"){
                //code for adding item to database
               // Toast.makeText(this,"Adding item...",Toast.LENGTH_SHORT).show()
                this.saveImageToStorage("test_image.jpg")
            }
            else{
                //code for help me match
                Toast.makeText(this,"Matching item...",Toast.LENGTH_SHORT).show()
            }
        }


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
                item_photo_hex_info.text = "RGB: $r, $g, $b \n HEX: $hex"
            }
            true
        }
    }


    private fun saveImageToStorage(url:String) {
        val externalStorageState = Environment.getExternalStorageState()
        if(externalStorageState == Environment.MEDIA_MOUNTED){
            val storageDirectory = Environment.getExternalStorageDirectory().toString()

            val file = File(storageDirectory,url)
            try {
                val stream:OutputStream = FileOutputStream(file)
                val drawble = ContextCompat.getDrawable(applicationContext,R.drawable.outfits)
                val bmp = (drawble as BitmapDrawable).bitmap
                bmp.compress(Bitmap.CompressFormat.JPEG,100,stream)
                stream.flush()
                stream.close()
                Toast.makeText(this,"Stored successfully ${Uri.parse(file.absolutePath)}",Toast.LENGTH_SHORT).show()
           }catch (e:Exception){
                e.printStackTrace()
                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
           }
        }else{
            Toast.makeText(this,"Unable to save media to storage",Toast.LENGTH_SHORT).show()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //if (requestCode === 123){
            //camera action here
            //var bmp = data?.extras?.get("data") as Bitmap
            //item_photo_editor.setImageBitmap(bmp)
            // crop
            if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {


                var result = CropImage.getActivityResult(data)
                item_photo_editor.setImageURI(result.uri)
            }

            else{
                Toast.makeText(this, "aaa", Toast.LENGTH_SHORT).show()
            }
       // }

       /* if (resultCode == Activity.RESULT_OK && requestCode == 1234){
            item_photo_editor.setImageURI(data?.data)
        }*/
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
