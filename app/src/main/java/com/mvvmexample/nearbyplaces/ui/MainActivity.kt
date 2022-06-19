package com.mvvmexample.nearbyplaces.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.mvvmexample.nearbyplaces.common.ui.location.LocationManagerHelper
import com.mvvmexample.nearbyplaces.common.ui.theme.NearbyTheme
import com.mvvmexample.nearbyplaces.navigation.AppNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var locationManagerHelper: LocationManagerHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            NearbyTheme {
                Surface(Modifier.fillMaxSize()) {
                    AppNavigator(
                        navController = navController,
                        locationManagerHelper = locationManagerHelper
                    )
                }
            }

        }
    }

}