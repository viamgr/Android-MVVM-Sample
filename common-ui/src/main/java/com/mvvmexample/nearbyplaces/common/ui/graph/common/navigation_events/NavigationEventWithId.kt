package com.mvvmexample.nearbyplaces.common.ui.graph.common.navigation_events

fun interface NavigationEventWithId<T> {
    operator fun invoke(id: Long)
}