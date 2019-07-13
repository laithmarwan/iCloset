package com.example.icloset

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_image_slider.view.*

class ImageSlider : AppCompatActivity() {

    internal lateinit var viewPager: ViewPager

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_slider)


        //weather API

        var lon = 0.0
        var lat = 0.0
        var pd = ProgressDialog(this)
        pd.setMessage("Please Wait...")
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        pd.show()
        val locationManager:LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val hasGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val hasNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        lateinit var locationGPS:Location
        lateinit var locationNetwork:Location
        if(hasGPS || hasNetwork){
            if(hasGPS){
                Log.d("CodeAndroidLocation", "hasGPS")
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,0f,object:LocationListener{
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
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,5000,0f,object:LocationListener{
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
            if(locationGPS != null && locationNetwork != null) {
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
            Toast.makeText(this,"problem",Toast.LENGTH_LONG).show()
        }

        var rq = Volley.newRequestQueue(this)

        val key = "fe7e0122aa2663a7f2aa546b5e121a59"
        var jo = JsonObjectRequest(Request.Method.GET,
            "api.openweathermap.org/data/2.5/weather?lat=$lat&lon=$lon&appid=$key",
            null, Response.Listener {
                    response ->
                        val arr = response.getJSONArray("weather")
                        val ob = arr.getJSONObject(0)
                        pd.hide()
                        val city = response.getString("")



            }, Response.ErrorListener {
                    error ->
                pd.hide()
                Toast.makeText(
                    this, error.message,
                    Toast.LENGTH_LONG
                ).show()
            })

        rq.add(jo)

        val obj = icloset(this)
        val db = obj.readableDatabase
        val cur = db.rawQuery("select * from outfit where occasion = ?", arrayOf()) //and weather = ?



        viewPager = findViewById<View>(R.id.viewPager) as ViewPager
        val adapter = ViewPageAdapter(this)
        viewPager.adapter = adapter
    }
}
