package com.mvvmexample.nearbyplaces.common.ui.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class LocationManagerHelper @Inject constructor(@ApplicationContext private val context: Context) {


    private val locationManager by lazy {
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    private val minDistance = 100f
    private val minTime: Long = 500

    fun observeLocation(
        onLocationChanged: (lat: Double, lan: Double) -> Unit,
        onLocationDisabled: () -> Unit,
    ) {
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            if (
                ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    minTime,
                    minDistance
                ) {
                    onLocationChanged(it.latitude, it.longitude)
                }
            }
        } else {
            onLocationDisabled()
        }
    }
}