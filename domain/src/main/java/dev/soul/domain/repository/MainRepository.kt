package dev.soul.domain.repository

import dev.soul.domain.common.Resource
import dev.soul.domain.model.booking.BookingDataModel
import dev.soul.domain.model.hotel.HotelModel
import dev.soul.domain.model.rooms.RoomsHeaderModel
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun getRooms():Flow<Resource<RoomsHeaderModel>>
    suspend fun getHotel():Flow<Resource<HotelModel>>
    suspend fun getBookingData():Flow<Resource<BookingDataModel>>
}