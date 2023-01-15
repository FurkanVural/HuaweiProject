package com.example.furkan1904569.ui

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import android.location.LocationRequest
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.example.furkan1904569.Manifest
import com.example.furkan1904569.R
import com.google.android.material.tabs.TabLayout.TabGravity
import com.huawei.agconnect.auth.internal.a.e
import kotlinx.coroutines.NonCancellable.message


class Location_kit : Fragment() {

    companion object {
        private const val TAG = "LocationMainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.location_main_activity)

        //You must have the ACCESS_COARSE_LOCATION or ACCESS_FINE_LOCATION permission. Otherwise, the location service is unavailable.

        // check location permission
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
            Log.i(TAG, "sdk < 28 Q")
            if (ActivityCompat.checkSelfPermission(
                    this,
                    ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                val strings = arrayOf(
                    ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                )
                ActivityCompat.requestPermissions(this, strings, 1)
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                val strings = arrayOf(ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                )
                ActivityCompat.requestPermissions(this, strings, 3)
            } else {
                ActivityResultContracts.RequestPermission.requestBackgroundPermission(this)
            }
        } else {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    "android.permission.ACCESS_BACKGROUND_LOCATION"
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                val strings = arrayOf(
                    ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
                    "android.permission.ACCESS_BACKGROUND_LOCATION"
                )
                ActivityCompat.requestPermissions(this, strings, 2)
            }
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.size > 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Log.i(TAG, "onRequestPermissionsResult: apply LOCATION PERMISSION successful")
            } else {
                Log.i(TAG, "onRequestPermissionsResult: apply LOCATION PERMISSION  failed")
            }
        }
        if (requestCode == 2) {
            if (grantResults.size > 2 && grantResults[2] == PackageManager.PERMISSION_GRANTED && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED
            ) {
                Log.i(
                    TAG,
                    "onRequestPermissionsResult: apply ACCESS_BACKGROUND_LOCATION successful"
                )
            } else {
                Log.i(TAG, "onRequestPermissionsResult: apply ACCESS_BACKGROUND_LOCATION  failed")
            }
        }
        if (requestCode == 3) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
                || grantResults[1] == PackageManager.PERMISSION_GRANTED
            ) {
                Log.i(
                    TAG,
                    "onRequestPermissionsResult: apply LOCATION PERMISSION successful"
                )
                ActivityResultContracts.RequestPermission.requestBackgroundPermission(this)
            } else {
                Log.i(
                    TAG,
                    "onRequestPermissionsResult: apply LOCATION PERMISSSION  failed"
                )
            }
        }
    }
}