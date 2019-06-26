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
            val dialog = BottomSheetDialog(requireContext())
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
            val dialog = BottomSheetDialog(requireActivity())
            val view = layoutInflater.inflate(R.layout.dialog_help_layout, null)

            val camera_help = view.findViewById<TextView>(R.id.camera_help_button)
            camera_help.setOnClickListener {
                if (Build.VERSION.SDK_INT > 22) {


                        requestPermissions(arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.READ_EXTERNAL_STORAGE),
                            HomeFragment.camera_code)

                   /* else
                    {
                        var i = Intent(activity, PhotoEditReview::class.java)
                        //AppInfo.act = "cam"
                        startActivity(i)
                    }*/
                }
                else
                {
                    //crop
                    //CropImage.activity()
                        //.setGuidelines(CropImageView.Guidelines.ON)
                        //.start(Activity())
                    var i = Intent(activity, PhotoEditReview::class.java)
                    //AppInfo.act = "cam"
                    startActivity(i)
                }
            }

            /*val media = view.findViewById<TextView>(R.id.media_button)
            media.setOnClickListener {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if(checkSelfPermission(requireContext(),android.Manifest.permission.READ_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_DENIED) {
                        ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                            HomeFragment.permission_code)
                    }
                    else{
                        var i = Intent(activity, PhotoEditReview::class.java)
                        AppInfo.act = "media"
                        startActivity(i)
                    }
                }
                else{
                    var i = Intent(activity, PhotoEditReview::class.java)
                    AppInfo.act = "media"
                    startActivity(i)
                }
            }*/

            val closet_help = view.findViewById<TextView>(R.id.closet_help_button)
            closet_help.setOnClickListener {
                //Go to closet
            }


            dialog.setContentView(view)
            dialog.show()
        }
        return v


    }


    companion object {
        //private val permission_code = 1001
        private val camera_code = 1002
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == camera_code){
            /* permission_code -> {
                 if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                     var i = Intent(activity, PhotoEditReview::class.java)
                     //i.putExtra("act", "media")
                     startActivity(i)
                 }

                 else
                 {
                     //
                 }
             */
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                var i = Intent(activity, PhotoEditReview::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(i)
            }

            else
            {
                Toast.makeText(activity,"Permissions must be granted",Toast.LENGTH_SHORT).show()
            }
        }
    }

}

