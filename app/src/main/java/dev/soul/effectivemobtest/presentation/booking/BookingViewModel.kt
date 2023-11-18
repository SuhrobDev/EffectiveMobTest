package dev.soul.effectivemobtest.presentation.booking

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.soul.domain.common.Resource
import dev.soul.domain.model.booking.BookingDataModel
import dev.soul.domain.use_case.UseCaseMain
import dev.soul.effectivemobtest.common.BaseViewModel
import dev.soul.effectivemobtest.common.models.UIObjectState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookingViewModel @Inject constructor(
    private val useCase: UseCaseMain
) : BaseViewModel() {


    private val _bookingData = MutableStateFlow(UIObjectState<BookingDataModel>())
    val getBookingData = _bookingData.asStateFlow()

    init {
        getBookingData()
    }

    private fun getBookingData() = viewModelScope.launch {
        useCase.bookingUseCase.getBookingData().collectLatest {
            when (it) {
                is Resource.Loading -> {
                    _bookingData.value = UIObjectState(isLoading = true)
                }

                is Resource.Success -> {
                    _bookingData.value = UIObjectState(data = it.data)
                }

                is Resource.Error -> {
                    _bookingData.value = UIObjectState(error = it.message.toString())
                }
            }
        }
    }
}