package com.example.icloset


import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Handler
import android.provider.MediaStore
import android.support.design.widget.BaseTransientBottomBar
import android.support.design.widget.BottomSheetDialog
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.checkSelfPermission
import android.support.v4.content.ContextCompat.getSystemService
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_photo_edit_review.*
import kotlinx.android.synthetic.main.dialog_layout.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.util.jar.Manifest

class HomeFragment : Fragment() {
    private lateinit var viewPager: ViewPager
    private lateinit var tv: TextView
    private lateinit var myDialog: Dialog
    lateinit var occasion:String
    var lon = 0.0
    var lat = 0.0
    @SuppressLint("NewApi", "MissingPermission")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for activity fragment
        var v = inflater.inflate(R.layout.fragment_home, container, false)

        if(AppInfo.Gender == "0"){
            v.dress_me_up_girl.visibility=View.VISIBLE
            v.dress_me_up_girl.isClickable=true
            v.dress_me_up.isClickable=false
            v.dress_me_up.visibility = View.INVISIBLE

            v.help_me_match_girl.visibility=View.VISIBLE
            v.help_me_match_girl.isClickable=true
            v.help_me_match.isClickable=false
            v.help_me_match.visibility= View.INVISIBLE
        }
        if(AppInfo.Gender == "1"){
            v.dress_me_up_girl.visibility=View.INVISIBLE
            v.dress_me_up_girl.isClickable=false
            v.dress_me_up.isClickable=true
            v.dress_me_up.visibility = View.VISIBLE

            v.help_me_match_girl.visibility=View.INVISIBLE
            v.help_me_match_girl.isClickable=false
            v.help_me_match.isClickable=true
            v.help_me_match.visibility= View.VISIBLE
        }


        v.dress_me_up_girl.setOnClickListener {
            val permissionLocation = ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
            if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
                getData()
            }
            else {
                caseDenied()
            }

            val dialog = BottomSheetDialog(requireContext())
            val view = layoutInflater.inflate(R.layout.dialog_layout, null)

            val gym = view.findViewById<TextView>(R.id.gym_button)
            gym.setOnClickListener {

                occasion = "Gym"
                weatherAPI()

            }
            val friends = view.findViewById<TextView>(R.id.friends_button)
            friends.setOnClickListener {
                occasion = "Friends"
                weatherAPI()

            }
            val party = view.findViewById<TextView>(R.id.party_button)
            party.setOnClickListener {

                occasion = "Party"
                weatherAPI()
            }
            val wedding = view.findViewById<TextView>(R.id.wedding_button)
            wedding.setOnClickListener {

                occasion = "Wedding"
                weatherAPI()
            }
            val school = view.findViewById<TextView>(R.id.school_button)
            school.setOnClickListener {

                occasion = "School"
                weatherAPI()
            }

            val work = view.findViewById<TextView>(R.id.work_button)
            work.setOnClickListener {

                occasion = "Work"
                weatherAPI()
            }
            val restaurant = view.findViewById<TextView>(R.id.restaurant_button)
            restaurant.setOnClickListener {

                occasion = "Restaurant"
                weatherAPI()
            }
            val trip = view.findViewById<TextView>(R.id.trip_button)
            trip.setOnClickListener {

                occasion = "Trip"
                weatherAPI()
            }
            val other = view.findViewById<TextView>(R.id.other_button)
            other.setOnClickListener {

                occasion = "Other"
                weatherAPI()
            }

            dialog.setContentView(view)
            dialog.show()
        }



        v.dress_me_up.setOnClickListener {
            val permissionLocation = ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
            if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
                getData()
            }
            else {
                caseDenied()
            }

            val dialog = BottomSheetDialog(requireContext())
            val view = layoutInflater.inflate(R.layout.dialog_layout, null)

            val gym = view.findViewById<TextView>(R.id.gym_button)
            gym.setOnClickListener {

                occasion = "Gym"
                weatherAPI()

            }
            val friends = view.findViewById<TextView>(R.id.friends_button)
            friends.setOnClickListener {

                occasion = "Friends"
                weatherAPI()

            }
            val party = view.findViewById<TextView>(R.id.party_button)
            party.setOnClickListener {

                occasion = "Party"
                weatherAPI()
            }
            val wedding = view.findViewById<TextView>(R.id.wedding_button)
            wedding.setOnClickListener {

                occasion = "Wedding"
                weatherAPI()
            }
            val school = view.findViewById<TextView>(R.id.school_button)
            school.setOnClickListener {

                occasion = "School"
                weatherAPI()
            }

            val work = view.findViewById<TextView>(R.id.work_button)
            work.setOnClickListener {

                occasion = "Work"
                weatherAPI()
            }
            val restaurant = view.findViewById<TextView>(R.id.restaurant_button)
            restaurant.setOnClickListener {

                occasion = "Restaurant"
                weatherAPI()
            }
            val trip = view.findViewById<TextView>(R.id.trip_button)
            trip.setOnClickListener {

                occasion = "Trip"
                weatherAPI()
            }
            val other = view.findViewById<TextView>(R.id.other_button)
            other.setOnClickListener {

                occasion = "Other"
                weatherAPI()
            }

            dialog.setContentView(view)
            dialog.show()
        }

        v.help_me_match_girl.setOnClickListener {
            val dialog = BottomSheetDialog(requireActivity())
            val view = layoutInflater.inflate(R.layout.dialog_help_layout, null)
            AppInfo.act = "help"

            val camera_help = view.findViewById<TextView>(R.id.camera_help_button)
            camera_help.setOnClickListener {
                if (Build.VERSION.SDK_INT > 22) {
                    requestPermissions(arrayOf(android.Manifest.permission.CAMERA,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE),
                        camera_code)
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

        v.help_me_match.setOnClickListener {
            val dialog = BottomSheetDialog(requireActivity())
            val view = layoutInflater.inflate(R.layout.dialog_help_layout, null)
            AppInfo.act = "help"
            val camera_help = view.findViewById<TextView>(R.id.camera_help_button)
            camera_help.setOnClickListener {
                if (Build.VERSION.SDK_INT > 22) {
                    requestPermissions(arrayOf(android.Manifest.permission.CAMERA,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE),
                        camera_code)
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

    }

    fun weatherAPI(){
        AppInfo.occ = occasion
        myDialog = Dialog(activity)
        myDialog.setContentView(R.layout.activity_image_slider)

        tv = myDialog.findViewById(R.id.tv_empty)


        val occasion = AppInfo.occ
        val obj = icloset(requireContext())
        val db = obj.readableDatabase
        val mArray:ArrayList<Outfit> = ArrayList()
        val cur = db.rawQuery("select * from outfit o,outfit_occasion oo,outfit_weather ow" +
                " where o.Outfit_ID = oo.Outfit_ID and oo.Outfit_ID = ow.Outfit_ID and ow.Weather = ? and oo.Occasion = ?", arrayOf(AppInfo.season,occasion))
        if(cur.count !=0){
            cur.moveToFirst()

            while(!cur.isAfterLast){
                mArray.add(Outfit(cur.getString(0),cur.getString(2),cur.getInt(5),cur.getString(7)))
                cur.moveToNext()
            }
        }
        else{
            Toast.makeText(activity,"empty",Toast.LENGTH_LONG).show()
            tv.text = "No items found relating to your occasion"
        }

        Toast.makeText(activity, AppInfo.season,Toast.LENGTH_LONG).show()

        viewPager = myDialog.findViewById(R.id.viewPager)

        val adapter = ViewPageAdapter(requireContext(),mArray)
        viewPager.adapter = adapter

        myDialog.show()
    }
    @SuppressLint("MissingPermission")
    private fun getData(){

        val locationManager = activity!!.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val hasGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val hasNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        lateinit var locationGPS: Location
        lateinit var locationNetwork: Location
        if(hasGPS || hasNetwork){
            if(hasGPS){
                Log.d("CodeAndroidLocation", "hasGPS")
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,0f,object: LocationListener {
                    override fun onLocationChanged(p0: Location) {
                        locationGPS =p0


                    }

                    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {

                    }

                    override fun onProviderEnabled(p0: String?) {

                    }

                    override fun onProviderDisabled(p0: String?) {

                    }
                })
                val local = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                if(local != null){
                    locationGPS = local
                }

            }
            if(hasNetwork){
                Log.d("CodeAndroidLocation", "hasGPS")
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,5000,0f,object:
                    LocationListener {
                    override fun onLocationChanged(p0: Location) {
                        locationNetwork =p0


                    }

                    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {

                    }

                    override fun onProviderEnabled(p0: String?) {

                    }

                    override fun onProviderDisabled(p0: String?) {

                    }
                })
                val local = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                if(local != null){
                    locationNetwork= local
                }

            }
            if(locationGPS == null){
                if(locationNetwork != null){
                    lon = locationNetwork.longitude
                    lat = locationNetwork.latitude
                }
                else{
                    if(locationGPS.accuracy > locationNetwork.accuracy){
                        lon = locationGPS.longitude
                        lat = locationGPS.latitude
                    }
                    else{
                        lon = locationNetwork.longitude
                        lat = locationNetwork.latitude
                    }
                }
            }
            else if(locationNetwork == null){
                lon = locationGPS.longitude
                lat = locationGPS.latitude
            }
            else{
                if(locationGPS.accuracy > locationNetwork.accuracy){
                    lon = locationGPS.longitude
                    lat = locationGPS.latitude
                }
                else{
                    lon = locationNetwork.longitude
                    lat = locationNetwork.latitude
                }
            }

        }else{
            Toast.makeText(activity,"problem",Toast.LENGTH_LONG).show()
        }
        val rq = Volley.newRequestQueue(activity)
        val key = "fe7e0122aa2663a7f2aa546b5e121a59"
        val jo = JsonObjectRequest(Request.Method.GET,
            "http://api.openweathermap.org/data/2.5/weather?lat=$lat&lon=$lon&appid=$key",
            null, Response.Listener {
                    response ->
                val ob = response.getJSONObject("main")
                var maxtemp = ob.getDouble("temp_max")
                var mintemp = ob.getDouble("temp_min")

                maxtemp -= 273.15
                mintemp -= 273.15
                val avgtemp = (maxtemp +mintemp)/2
                AppInfo.season = when {
                    avgtemp < 15.0 -> "Winter"
                    avgtemp < 20.0 -> "Autumn"
                    avgtemp < 25.0 -> "Spring"
                    else -> "Summer"
                }


            }, Response.ErrorListener {
                    error ->

                Toast.makeText(
                    activity, error.message,
                    Toast.LENGTH_LONG
                ).show()
            })

        rq.add(jo)


    }
    private fun caseDenied(){
        val rq = Volley.newRequestQueue(activity)
        val key = "fe7e0122aa2663a7f2aa546b5e121a59"
        val jo = JsonObjectRequest(Request.Method.GET,
            "http://api.openweathermap.org/data/2.5/weather?q=${AppInfo.Address}&appid=$key",
            null, Response.Listener {
                    response ->
                val ob = response.getJSONObject("main")
                var maxtemp = ob.getDouble("temp_max")
                var mintemp = ob.getDouble("temp_min")

                maxtemp -= 273.15
                mintemp -= 273.15
                val avgtemp = (maxtemp +mintemp)/2
                AppInfo.season = when {
                    avgtemp < 15.0 -> "Winter"
                    avgtemp < 20.0 -> "Autumn"
                    avgtemp < 25.0 -> "Spring"
                    else -> "Summer"
                }


            }, Response.ErrorListener {
                    error ->

                Toast.makeText(
                    activity, error.message,
                    Toast.LENGTH_LONG
                ).show()
            })

        rq.add(jo)
    }

}

