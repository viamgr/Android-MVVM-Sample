package com.mvvmexample.nearbyplaces.ui.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.mvvmexample.nearbyplaces.common.ui.graph.common.composable
import com.mvvmexample.nearbyplaces.common.ui.location.LocationManagerHelper
import com.mvvmexample.nearbyplaces.ui.screen.detail.PlaceDetailScreen
import com.mvvmexample.nearbyplaces.ui.screen.home.PlacesScreen

private fun NavGraphBuilder.addPlaceHomeGraphScreen(
    navController: NavHostController,
    locationManagerHelper: LocationManagerHelper
) {
    composable(
        screen = NearByGraph.PlacesMain
    ) {
        PlacesScreen(locationManagerHelper = locationManagerHelper) {
            navController.navigate(NearByGraph.PlaceDetailScreen().createRoute(it))
        }
    }
}

private fun NavGraphBuilder.addPlaceDetailGraphScreen(navController: NavHostController) {
    composable(
        screen = NearByGraph.PlaceDetailScreen()
    ) {

        PlaceDetailScreen {
            navController.navigateUp()
        }
    }
}


fun NavGraphBuilder.addPlacesGraph(
    navController: NavHostController,
    locationManagerHelper: LocationManagerHelper
) {
    navigation(
        route = NearByGraph.route,
        startDestination = NearByGraph.PlacesMain.route
    ) {
        addPlaceHomeGraphScreen(navController, locationManagerHelper)
        addPlaceDetailGraphScreen(navController)
    }
}