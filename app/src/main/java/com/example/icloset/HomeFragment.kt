package com.example.icloset


import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.provider.MediaStore
import android.support.design.widget.BaseTransientBottomBar
import android.support.design.widget.BottomSheetDialog
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.checkSelfPermission
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_photo_edit_review.*
import kotlinx.android.synthetic.main.dialog_layout.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.util.jar.Manifest

class HomeFragment : Fragment() {
    lateinit var occasion:String
    @SuppressLint("NewApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_home, container, false)

        v.dress_me_up.setOnClickListener {


            val dialog = BottomSheetDialog(requireContext())
            val view = layoutInflater.inflate(R.layout.dialog_layout, null)

            val gym = view.findViewById<TextView>(R.id.gym_button)
            gym.setOnClickListener {
                checkPermissions()
                occasion = "Gym"

            }
            val friends = view.findViewById<TextView>(R.id.friends_button)
            friends.setOnClickListener {
                checkPermissions()
                occasion = "Friends"

            }
            val party = view.findViewById<TextView>(R.id.party_button)
            party.setOnClickListener {
                checkPermissions()
                occasion = "Party"
            }
            val wedding = view.findViewById<TextView>(R.id.wedding_button)
            wedding.setOnClickListener {
                checkPermissions()
                occasion = "Wedding"
            }
            val school = view.findViewById<TextView>(R.id.school_button)
            school.setOnClickListener {
                checkPermissions()
                occasion = "School"
            }

            val work = view.findViewById<TextView>(R.id.work_button)
            work.setOnClickListener {
                checkPermissions()
                occasion = "Work"
            }
            val restaurant = view.findViewById<TextView>(R.id.restaurant_button)
            restaurant.setOnClickListener {
                checkPermissions()
                occasion = "Restaurant"
            }
            val trip = view.findViewById<TextView>(R.id.trip_button)
            trip.setOnClickListener {
                checkPermissions()
                occasion = "Trip"
            }
            val other = view.findViewById<TextView>(R.id.other_button)
            other.setOnClickListener {
                checkPermissions()
                occasion = "Other"
            }

            dialog.setContentView(view)
            dialog.show()
        }

        v.help_me_match.setOnClickListener {
            val dialog = BottomSheetDialog(requireActivity())
            val view = layoutInflater.inflate(R.layout.dialog_help_layout, null)

            val camera_help = view.findViewById<TextView>(R.id.camera_help_button)
            camera_help.setOnClickListener {
                if (Build.VERSION.SDK_INT > 22) {
                    requestPermissions(arrayOf(android.Manifest.permission.CAMERA,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE),
                        HomeFragment.camera_code)
                }
                else
                {

                    var crm = CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)

                    crm.start(requireActivity())

                }
            }

            val closet_help = view.findViewById<TextView>(R.id.closet_help_button)
            closet_help.setOnClickListener {
                //Go to closet
            }


            dialog.setContentView(view)
            dialog.show()
        }
        return v


    }

    private fun checkPermissions(){
        if (Build.VERSION.SDK_INT > 22) {
            requestPermissions(arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION),2)

        }
        else
        {

            val i = Intent(requireContext(),ImageSlider::class.java)
            i.putExtra("occasion",occasion)
            startActivity(i)
        }
    }
    companion object {
        //private val permission_code = 1001
        private val camera_code = 1002
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == camera_code){

            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                var crm = CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)

                crm.start(requireActivity())

            }

            else
            {
                Toast.makeText(activity,"Permissions must be granted",Toast.LENGTH_SHORT).show()
            }
        }
        else if(requestCode == 2){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                val i = Intent(requireContext(),ImageSlider::class.java)
                i.putExtra("occasion",occasion)
                startActivity(i)
            }
            else
            {
                Toast.makeText(activity,"Permissions must be granted",Toast.LENGTH_SHORT).show()
            }
        }
    }



}

