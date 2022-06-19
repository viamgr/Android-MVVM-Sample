package com.mvvmexample.nearbyplaces.ui.screen.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mvvmexample.nearbyplaces.common.ui.graph.common.navigation_events.NavigationEvent
import com.mvvmexample.nearbyplaces.ui.R

@Composable
fun PlaceDetailScreen(
    navigateBack: NavigationEvent,
) {
    PlaceDetailScreen(navigateBack = navigateBack, viewModel = hiltViewModel())
}


@Composable
private fun PlaceDetailScreen(
    navigateBack: NavigationEvent,
    viewModel: PlaceDetailViewModel
) {
    val place = viewModel.place.collectAsState(null).value
    if (place != null) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp, horizontal = 10.dp)
        ) {
            Text(
                text = "<- ${place.poi?.name}",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.clickable {
                    navigateBack()
                })
            Text(text = "Score: ${place.score}", style = MaterialTheme.typography.body2)
            Text(text = "Phone: ${place.poi?.phone ?: "-"}", style = MaterialTheme.typography.body2)
            Text(text = "Distance: ${place.dist}", style = MaterialTheme.typography.body2)
            Text(
                text = "Street Number: ${place.address?.localName}",
                style = MaterialTheme.typography.body2
            )

            place.entryPoints?.also {
                Text(
                    text = "Entry Pints:",
                    style = MaterialTheme.typography.subtitle1

                )
            }?.forEach {
                Text(
                    text = it.type,
                    modifier = Modifier.padding(12.dp, 0.dp),
                    style = MaterialTheme.typography.subtitle2
                )
            }

            place.dataSources?.poiDetails?.also {
                Text(
                    text = "DataSources:",
                    style = MaterialTheme.typography.subtitle1
                )
            }?.forEach {
                Text(
                    text = it.sourceName,
                    modifier = Modifier.padding(12.dp, 0.dp),
                    style = MaterialTheme.typography.subtitle2
                )
            }

            Text(
                text = "Viewport:",
                style = MaterialTheme.typography.subtitle1
            )

            place.viewport?.also {
                Text(
                    text = "BtmRightPoint: lat:${it.btmRightPoint.lat} -  lon:${it.btmRightPoint.lon}",
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.padding(12.dp, 0.dp),
                )
                Text(
                    text = "TopLeftPoint: lat:${it.topLeftPoint.lat} - lon:${it.topLeftPoint.lon}",
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.padding(12.dp, 0.dp),

                    )
            }
        }


    } else {
        Text(stringResource(R.string.not_found_item), Modifier.clickable {
            navigateBack()
        })
    }
}