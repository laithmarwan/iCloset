package com.example.icloset


import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.app.Fragment
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.provider.MediaStore
import android.support.design.widget.BottomSheetDialog
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat.checkSelfPermission
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.dialog_layout.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.util.jar.Manifest


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : Fragment() {

    @SuppressLint("NewApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_home, container, false)

        v.dress_me_up.setOnClickListener {
            val dialog = BottomSheetDialog(activity)
            val view = layoutInflater.inflate(R.layout.dialog_layout, null)

            val gym = view.findViewById<TextView>(R.id.gym_button)
            gym.setOnClickListener {

            }
            val party = view.findViewById<TextView>(R.id.party_button)
            party.setOnClickListener {

            }
            val wedding = view.findViewById<TextView>(R.id.wedding_button)
            wedding.setOnClickListener {

            }
            val school = view.findViewById<TextView>(R.id.school_button)
            school.setOnClickListener {

            }

            val work = view.findViewById<TextView>(R.id.work_button)
            work.setOnClickListener {

            }
            val restaurant = view.findViewById<TextView>(R.id.restaurant_button)
            restaurant.setOnClickListener {

            }
            val trip = view.findViewById<TextView>(R.id.trip_button)
            trip.setOnClickListener {

            }
            val other = view.findViewById<TextView>(R.id.other_button)
            other.setOnClickListener {

            }

            dialog.setContentView(view)
            dialog.show()
        }

        v.help_me_match.setOnClickListener {
            val dialog = BottomSheetDialog(activity)
            val view = layoutInflater.inflate(R.layout.dialog_help_layout, null)

            val camera_help = view.findViewById<TextView>(R.id.camera_help_button)
            camera_help.setOnClickListener {
                if (Build.VERSION.SDK_INT > 22) {
                    if (checkSelfPermission(getContext(), android.Manifest.permission.CAMERA) ==
                        PackageManager.PERMISSION_DENIED) {
                        ActivityCompat.requestPermissions(activity, arrayOf(android.Manifest.permission.CAMERA), camera_code)
                    }
                    else {
                        openCamera()
                    }
                }
                else{
                    openCamera()
                }
            }

            val media = view.findViewById<TextView>(R.id.media_button)
            media.setOnClickListener {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if(checkSelfPermission(getContext(),android.Manifest.permission.READ_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_DENIED) {
                        ActivityCompat.requestPermissions(activity, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), permission_code)
                    }
                    else{
                        pickImageFromGallery()
                    }
                }
                else{
                    pickImageFromGallery()
                }
            }

            val closet_help = view.findViewById<TextView>(R.id.closet_help_button)
            closet_help.setOnClickListener {

            }

            dialog.setContentView(view)
            dialog.show()
        }
        return v
    }


    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, image_pick_code)
    }

    private fun openCamera(){
        var i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(i, 123)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === 123){
            var bmp = data.extras.get("data") as Bitmap
            //camera action here
        }

        if (resultCode == Activity.RESULT_OK && requestCode == image_pick_code){
            // pick image action here
        }
    }

    companion object {
        private val image_pick_code = 1000
        private val permission_code = 1001
        private val camera_code = 1002
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when(requestCode){
            permission_code -> {
                if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery()
                }
                else{
                    //
                }
            }
            camera_code -> {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    openCamera()
                }
                else{
                    //
                }
            }
        }
    }
}
