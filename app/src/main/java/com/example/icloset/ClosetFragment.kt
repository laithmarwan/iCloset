package com.example.icloset


import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import android.widget.Toolbar
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.app_bar_main.view.*
import kotlinx.android.synthetic.main.fragment_closet.view.*

class ClosetFragment : Fragment() {

    lateinit var fab_main: FloatingActionButton
    lateinit var fab_sub_1: FloatingActionButton
    lateinit var fab_sub_2: FloatingActionButton
    lateinit var toolbar: Toolbar
    lateinit var fab_open: Animation
    lateinit var fab_close: Animation
    lateinit var rotate_cw: Animation
    lateinit var rotate_acw: Animation
    var isOpen: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      var v = inflater.inflate(R.layout.fragment_closet, container, false)






        var adp = FPA(childFragmentManager)
        v.vp.adapter = adp

        v.vp.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(v.tabs))
        lateinit var courses:Array<String>
        lateinit var icons:Array<Int>
        if(AppInfo.Gender == "0") {
            //Women's clothing: see our documentation interface
            courses= arrayOf("","","","","","","")
            icons = arrayOf(R.drawable.woman_tshirt,R.drawable.bottoms,R.drawable.woman_shoes,R.drawable.dresses,R.drawable.purses,R.drawable.shades,R.drawable.jacket)
        }
        else{
            //Men's clothing: don't include dresses icon
            courses= arrayOf("","","","","","")
            icons = arrayOf(R.drawable.men_tshirt,R.drawable.bottoms,R.drawable.men_shoes,R.drawable.man_bag,R.drawable.shades,R.drawable.jacket)
        }


        for(x in 0 until courses.size)
        {
            v.tabs.addTab(v.tabs.newTab().setText(courses[x]).setIcon(icons[x]))
        }
        v.tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(v.vp))




        var fab_open = AnimationUtils.loadAnimation(activity,R.anim.open_fab)
        var fab_close = AnimationUtils.loadAnimation(activity,R.anim.close_fab)
        var rotate_cw = AnimationUtils.loadAnimation(activity,R.anim.rotate_clockwise)
        var rotate_acw = AnimationUtils.loadAnimation(activity,R.anim.rotate_anticlockwise)
        v.add_btn.setOnClickListener{
            AppInfo.act = "add"
                /*if (isOpen)
                {
                    v.fab2.startAnimation(fab_close)
                    v.fab3.startAnimation(fab_close)
                    v.add_btn.startAnimation(rotate_acw)
                    v.fab2.visibility = View.GONE
                    v.fab3.visibility = View.GONE
                    isOpen = false
                }
                else
                {
                    v.fab2.startAnimation(fab_open)
                    v.fab3.startAnimation(fab_open)
                    v.add_btn.startAnimation(rotate_cw)
                    v.fab2.visibility = View.VISIBLE
                    v.fab3.visibility = View.VISIBLE
                    v.fab2.isClickable = true
                    v.fab3.isClickable = true
                    isOpen = true
                }*/




           if (Build.VERSION.SDK_INT > 22) {
               requestPermissions(arrayOf(android.Manifest.permission.CAMERA,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                   android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                   ClosetFragment.camera_code)

            }
            else
            {

                var crm = CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)

                crm.start(requireActivity())

            }
        }


        return v
    }
    companion object {
        //private val permission_code = 1001
        private val camera_code = 1002
    }
    class FPA(fm: FragmentManager) : FragmentPagerAdapter(fm)
    {
        override fun getItem(position: Int): Fragment {

            return when (position) {
                0 -> TopsFragment()
                1 -> BottomsFragment()
                2 -> ShoesFragment()
                3 -> {
                    if(AppInfo.Gender == "0")
                        DressesFragment()
                    else{
                        BagsFragment()
                    }

                }
                4 -> {
                    if(AppInfo.Gender == "0")
                        BagsFragment()
                    else{
                        AccessoriesFragment()
                    }

                }
                5 -> {
                    if(AppInfo.Gender == "0")
                        AccessoriesFragment()
                    else{
                        OuterwearFragment()
                    }

                }
                else -> OuterwearFragment()

            }

        }

        override fun getCount(): Int {
            return 7
        }

    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == camera_code){

            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED && grantResults[2] == PackageManager.PERMISSION_GRANTED){
                AppInfo.act = "add"
                var crm = CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)

                crm.start(requireActivity())

            }

            else
            {
                Toast.makeText(activity,"Permissions must be granted", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
