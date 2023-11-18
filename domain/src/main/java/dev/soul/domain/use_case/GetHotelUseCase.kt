package dev.soul.domain.use_case

import dev.soul.domain.common.Resource
import dev.soul.domain.model.booking.BookingDataModel
import dev.soul.domain.model.hotel.HotelModel
import dev.soul.domain.model.rooms.RoomsHeaderModel
import dev.soul.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHotelUseCase @Inject constructor(
    private val repository: MainRepository
) {
    suspend fun getHotel(): Flow<Resource<HotelModel>> = repository.getHotel()

}