package com.example.helloworld

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.app.ActivityCompat

class GpsActivity : AppCompatActivity() {

    protected lateinit var gpsLatitudeText : TextView
    protected lateinit var gpsLongitudeText : TextView
    protected lateinit var gpsAltitudeText : TextView
    protected lateinit var gpsLocationText : TextView

    protected lateinit var locationManager : LocationManager
    protected lateinit var geocoder : Geocoder

    protected val locationListener : LocationListener = object : LocationListener{
        override fun onLocationChanged(location: Location) {
            gpsLatitudeText.text = "${location.latitude}"
            gpsLongitudeText.text = "${location.longitude}"
            gpsAltitudeText.text = "${location.altitude}"

            val listAddr = geocoder.getFromLocation(location.latitude, location.longitude, 1)
            val addr = listAddr.get(0)
            var str = ""
            for(i in 0 .. addr.maxAddressLineIndex)
                str += addr.getAddressLine(i)
            gpsLocationText.text = str
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gps)

        gpsLatitudeText = findViewById(R.id.gpsLatitudeText)
        gpsLongitudeText = findViewById(R.id.gpsLongitudeText)
        gpsAltitudeText = findViewById(R.id.gpsAltitudeText)
        gpsLocationText = findViewById(R.id.gpsLocationText)

        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        geocoder = Geocoder(this)
    }

    override fun onResume() {
        super.onResume()
        val best : String? = locationManager.getBestProvider(Criteria(), true)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }

        if(best != null)
            locationManager.requestLocationUpdates(best as String, 5000, 5f, locationListener)
        else
            Log.e("GpsActivity.class", "GPS not Available.")
    }

    override fun onPause() {
        super.onPause()
        locationManager.removeUpdates(locationListener)
    }

    fun onBnClick(view : View){
        val i = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        startActivity(i)
    }
}

