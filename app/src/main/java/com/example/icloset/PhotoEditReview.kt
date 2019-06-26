package com.example.icloset

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.MotionEvent
import android.widget.Toast
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_photo_edit_review.*

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
       // }

       /* if (resultCode == Activity.RESULT_OK && requestCode == 1234){
            item_photo_editor.setImageURI(data?.data)
        }*/
    }

    private fun openCamera(){
        //var i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        CropImage.activity()
            .setGuidelines(CropImageView.Guidelines.ON)
        //startActivityForResult(i, 123)

        .start(this)

    }

   //private fun pickImageFromGallery() {
        //val intent = Intent(Intent.ACTION_PICK)
        //intent.type = "image/*"
        //startActivityForResult(intent, 1234)
   // }


}
