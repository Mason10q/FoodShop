package com.example.core_android

import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
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
        mLocationManager = context.getSystemService(LOCATION_SERVICE) as LocationManager
        val providers = mLocationManager!!.getProviders(true)
        var bestLocation: Location? = null

        for (provider in providers) {
            val l = mLocationManager!!.getLastKnownLocation(provider) ?: continue
            if (bestLocation == null || l.accuracy < bestLocation.accuracy) {
                bestLocation = l
            }
        }
        return Geocoder(context, Locale.getDefault()).getFromLocation(
            bestLocation?.latitude ?: 0.0,
            bestLocation?.longitude ?: 0.0,
            1
        )?.get(0)?.subAdminArea.toString()
    }

    fun getUserData(): UserData {
        return UserData(getLastKnownLocation(), getCurrentDate())
    }
}