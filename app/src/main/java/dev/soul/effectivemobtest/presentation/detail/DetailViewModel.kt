package dev.soul.effectivemobtest.presentation.detail

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.soul.domain.common.Resource
import dev.soul.domain.model.rooms.RoomsHeaderModel
import dev.soul.domain.use_case.GetRoomUseCase
import dev.soul.domain.use_case.UseCaseMain
import dev.soul.effectivemobtest.common.models.UIObjectState
import dev.soul.effectivemobtest.common.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: UseCaseMain
) : BaseViewModel() {

    private val _room = MutableStateFlow(UIObjectState<RoomsHeaderModel>())
    val getRooms = _room.asStateFlow()


    init {
        getRooms()
    }

    private fun getRooms() = viewModelScope.launch {
        useCase.roomUseCase.getRooms().collectLatest {
            when (it) {
                is Resource.Loading -> {
                    _room.value = UIObjectState(isLoading = true)
                }

                is Resource.Success -> {
                    _room.value = UIObjectState(data = it.data)
                }

                is Resource.Error -> {
                    _room.value = UIObjectState(error = it.message.toString())
                }

            }

        }
    }


}