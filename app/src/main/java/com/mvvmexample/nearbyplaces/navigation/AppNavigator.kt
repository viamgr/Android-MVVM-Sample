package com.mvvmexample.nearbyplaces.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mvvmexample.nearbyplaces.common.ui.location.LocationManagerHelper
import com.mvvmexample.nearbyplaces.ui.graph.NearByGraph
import com.mvvmexample.nearbyplaces.ui.graph.addPlacesGraph

@Composable
fun AppNavigator(
    navController: NavHostController,
    locationManagerHelper: LocationManagerHelper
) {
    NavHost(
        navController = navController,
        startDestination = NearByGraph.route,
    ) {
        addPlacesGraph(navController, locationManagerHelper)
    }
}
