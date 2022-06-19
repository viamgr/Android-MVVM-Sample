package com.mvvmexample.nearbyplaces.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mvvmexample.nearbyplaces.domain.usecases.GetLastLocation
import com.mvvmexample.nearbyplaces.domain.usecases.GetPagedPlaceListUseCase
import com.mvvmexample.nearbyplaces.domain.usecases.UpdateLocation
import com.mvvmexample.nearbyplaces.domain.utils.dataOrNull
import com.mvvmexample.nearbyplaces.model.Place
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlacesViewModel @Inject constructor(
    private val updateLocation: UpdateLocation,
    getPagedPlaceListUseCase: GetPagedPlaceListUseCase,
    getLastLocation: GetLastLocation,
) : ViewModel() {
    val hasLocation = getLastLocation(Unit).map {
        it.dataOrNull() != null
    }

    val pagedList: Flow<PagingData<Place>> =
        getPagedPlaceListUseCase().cachedIn(viewModelScope)

    fun onLocationChanged(lat: Double, lon: Double) {
        viewModelScope.launch {
            updateLocation(UpdateLocation.Location(lat, lon))
        }
    }

    fun onLocationDisabled() {

    }

}
