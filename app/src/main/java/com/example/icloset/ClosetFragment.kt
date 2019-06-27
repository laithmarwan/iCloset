package com.example.icloset


import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_closet.view.*

class ClosetFragment : Fragment() {

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

        v.add_btn.setOnClickListener {
           if (Build.VERSION.SDK_INT > 22) {
               requestPermissions(arrayOf(android.Manifest.permission.CAMERA,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE),ClosetFragment.camera_code)

            }
            else
            {
                var i = Intent(activity, PhotoEditReview::class.java)
                AppInfo.act = "add"
                startActivity(i)
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
                3 -> DressesFragment()
                4 -> BagsFragment()
                5 -> AccessoriesFragment()
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

            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                var i = Intent(activity, PhotoEditReview::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                AppInfo.act = "add"
                startActivity(i)
            }

            else
            {
                Toast.makeText(activity,"Permissions must be granted", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
