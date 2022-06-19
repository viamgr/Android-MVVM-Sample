package com.mvvmexample.nearbyplaces.ui.screen.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mvvmexample.nearbyplaces.domain.usecases.GetPlaceUseCase
import com.mvvmexample.nearbyplaces.domain.utils.dataOrNull
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class PlaceDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getPlaceUseCase: GetPlaceUseCase,
) : ViewModel() {
    val place =
        getPlaceUseCase(savedStateHandle.get<Long>("id") ?: error("Place Id is required")).map {
            it.dataOrNull()
        }

}
