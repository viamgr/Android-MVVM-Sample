package com.mvvmexample.nearbyplaces.ui.screen.home

import android.content.Intent
import android.net.Uri
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.items
import com.mvvmexample.nearbyplaces.common.ui.graph.common.navigation_events.NavigationEventWithId
import com.mvvmexample.nearbyplaces.common.ui.graph.screens.RequiresLocationPermission
import com.mvvmexample.nearbyplaces.common.ui.location.LocationManagerHelper
import com.mvvmexample.nearbyplaces.model.Place
import com.mvvmexample.nearbyplaces.ui.R

@Composable
fun PlacesScreen(
    locationManagerHelper: LocationManagerHelper,
    navigateToDetail: NavigationEventWithId<Long>

) {
    PlacesScreen(
        navigateToDetail = navigateToDetail,
        viewModel = hiltViewModel(),
        locationManagerHelper
    )
}

@Composable
private fun PlacesScreen(
    navigateToDetail: NavigationEventWithId<Long>,
    viewModel: PlacesViewModel,
    locationManagerHelper: LocationManagerHelper
) {
    RequiresLocationPermission(navigateToSetting()) {
        ObserveUserLocation(viewModel, locationManagerHelper)
        val hasLocation = viewModel.hasLocation.collectAsState(false)
        if (hasLocation.value) {
            ShowLocations(navigateToDetail, viewModel)
        } else {
            WaitForLocation()
        }
    }

}

@Composable
fun WaitForLocation() {
    Text(
        stringResource(R.string.waiting_for_location),
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
    )
}

@Composable
private fun ShowLocations(
    navigateToDetail: NavigationEventWithId<Long>,
    viewModel: PlacesViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        LazyColumnWithPaging(
            pagingFlowResult = viewModel.pagedList,
            onRetryFailure = { error, onRetry ->
                println("error")
            },
            onRetry = {

            },
            emptyMessage = {
                Text(text = stringResource(id = R.string.no_data_available))
            },
        ) { pagingItems ->
            items(pagingItems, key = { it.id }) { place ->
                if (place != null) {
                    PlaceItem(
                        Modifier.clickable {
                            navigateToDetail(place.id)
                        }, place
                    )
                }
            }
        }
    }
}


@Composable
private fun PlaceItem(
    modifier: Modifier,
    place: Place
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp, horizontal = 10.dp)
            .then(modifier)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(vertical = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .fillMaxHeight()
                    .padding(top = 5.dp, bottom = 5.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(text = "${place.poi?.name}", style = MaterialTheme.typography.h6)
                Text(text = "Score: ${place.score}", style = MaterialTheme.typography.body2)
                Text(text = "Phone: ${place.poi?.phone}", style = MaterialTheme.typography.body2)
                Text(text = "Distance: ${place.dist}", style = MaterialTheme.typography.caption)
                Text(
                    text = "Street Number: ${place.address?.localName}",
                    style = MaterialTheme.typography.caption
                )

            }

        }
    }
    Divider(
        modifier = Modifier.fillMaxWidth(), color = Color.LightGray, thickness = 1.dp
    )
}

@Composable
private fun navigateToSetting(): () -> Unit {
    val context = LocalContext.current
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        }
    val navigateToSettingsScreen = {
        launcher.launch(Intent(ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.fromParts("package", context.packageName, null)
        })
    }
    return navigateToSettingsScreen
}

@Composable
fun ObserveUserLocation(viewModel: PlacesViewModel, locationManagerHelper: LocationManagerHelper) {
    LaunchedEffect(Unit) {
        locationManagerHelper.observeLocation({ lat, lan ->
            viewModel.onLocationChanged(lat, lan)
        }, {
            viewModel.onLocationDisabled()
        })
    }
}
