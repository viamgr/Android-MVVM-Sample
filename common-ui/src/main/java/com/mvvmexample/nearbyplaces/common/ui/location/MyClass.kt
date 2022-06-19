package com.mvvmexample.nearbyplaces.common.ui.location

import android.content.Context
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

// No @Inject because this isn't instantiated in a Dagger context public
object MyClass {

    @EntryPoint
    @InstallIn(ActivityRetainedComponent::class)
    interface LocationEntryPoint {
        fun getLocationManagerHelper(): LocationManagerHelper
    }

    fun getLocationHelper(context: Context): LocationManagerHelper {
        val myClassInterface: Any =
            EntryPoints.get(context, LocationEntryPoint::class.java)

        return null!!
    }

}