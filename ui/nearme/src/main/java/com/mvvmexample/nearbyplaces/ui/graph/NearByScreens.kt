package com.mvvmexample.nearbyplaces.ui.graph

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.mvvmexample.nearbyplaces.common.ui.graph.common.EmptyArgumentScreen
import com.mvvmexample.nearbyplaces.common.ui.graph.common.Graph
import com.mvvmexample.nearbyplaces.ui.R

object NearByGraph : Graph {
    override val route: String = "places-graph"

    object PlacesMain : EmptyArgumentScreen(
        route = "home",
        graph = NearByGraph
    )

    class PlaceDetailScreen() : EmptyArgumentScreen(
        route = "detail/{id}",
        graph = NearByGraph,
        navArguments = listOf(
            navArgument("id") { type = NavType.LongType }
        )
    ) {
        fun createRoute(id: Long) = "detail/$id"
        override fun getLabelResourceId(): Int = R.string.place_detail
    }

}