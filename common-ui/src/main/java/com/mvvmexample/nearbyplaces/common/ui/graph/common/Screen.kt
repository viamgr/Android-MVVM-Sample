package com.mvvmexample.nearbyplaces.common.ui.graph.common

import androidx.navigation.NamedNavArgument

interface Screen {
    val graph: Graph
    val route: String
    var navArguments: List<NamedNavArgument>

    val absoluteRoute: String
        get() = "${graph.route}/$route"
}