package dev.soul.effectivemobtest.presentation.main

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.soul.domain.common.Resource
import dev.soul.domain.model.hotel.HotelModel
import dev.soul.domain.use_case.UseCaseMain
import dev.soul.effectivemobtest.common.BaseViewModel
import dev.soul.effectivemobtest.common.models.UIObjectState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: UseCaseMain
) : BaseViewModel() {

    private val _hotel = MutableStateFlow(UIObjectState<HotelModel>())
    val getHotel = _hotel.asStateFlow()


    init {
        getHotel()
    }

    private fun getHotel() = viewModelScope.launch {
        useCase.hotelUseCase.getHotel().collectLatest {

            when (it) {
                is Resource.Loading -> {
                    _hotel.value = UIObjectState(isLoading = true)
                }

                is Resource.Success -> {
                    _hotel.value = UIObjectState(data = it.data)
                }

                is Resource.Error -> {
                    _hotel.value = UIObjectState(error = it.message.toString())
                }
            }
        }
    }

}