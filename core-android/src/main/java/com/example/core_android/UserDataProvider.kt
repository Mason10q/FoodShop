package com.example.core_android

import android.Manifest
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import java.text.DateFormat
import java.util.Calendar
import java.util.Locale


class UserDataProvider(private val context: Context) {

    private fun getCurrentDate(): String {
        val date = Calendar.getInstance().time
        return DateFormat.getDateInstance().format(date)
    }

    private var mLocationManager: LocationManager? = null

    private fun getLastKnownLocation(): String {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return "Невозможно узнать город"
        } else {

            mLocationManager = context.getSystemService(LOCATION_SERVICE) as LocationManager
            val providers = mLocationManager!!.getProviders(true)
            var bestLocation: Location? = null

            for (provider in providers) {
                val l = mLocationManager!!.getLastKnownLocation(provider) ?: continue
                if (bestLocation == null || l.accuracy < bestLocation.accuracy) {
                    bestLocation = l
                }
            }

            val geocoder = (Geocoder(context, Locale.getDefault()).getFromLocation(
                bestLocation?.latitude ?: 0.0,
                bestLocation?.longitude ?: 0.0,
                1
            ))

            return if (geocoder?.size!! > 0){
                geocoder[0]?.subAdminArea.toString()
            } else {
                "Невозможно узнать город"
            }
        }
    }

    fun getUserData(): UserData {
        return UserData(getLastKnownLocation(), getCurrentDate())
    }
}