package com.mvvmexample.nearbyplaces.common.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun NearbyTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        content = content
    )
}