package com.mvvmexample.nearbyplaces.common.ui.graph.common

import androidx.annotation.StringRes
import androidx.navigation.NamedNavArgument

interface WithLabel {
    @StringRes
    fun getLabelResourceId(): Int = -1
}

abstract class EmptyArgumentScreen(
    override val route: String,
    override val graph: Graph,
    override var navArguments: List<NamedNavArgument> = emptyList(),
) : Screen, WithLabel
