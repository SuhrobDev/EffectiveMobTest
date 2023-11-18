package dev.soul.data.repository

import dev.soul.data.common.ResultHandler
import dev.soul.data.mapper.toModel
import dev.soul.data.remote.MainService
import dev.soul.domain.common.Resource
import dev.soul.domain.model.booking.BookingDataModel
import dev.soul.domain.model.hotel.HotelModel
import dev.soul.domain.model.rooms.RoomsHeaderModel
import dev.soul.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainService: MainService
) : MainRepository, ResultHandler() {

    override suspend fun getRooms(): Flow<Resource<RoomsHeaderModel>> =
        loadResult({ mainService.getRooms() }, { data, flow ->
            try {
                flow.emit(Resource.Success(data.toModel()))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun getHotel(): Flow<Resource<HotelModel>> =
        loadResult({ mainService.getHotel() }, { data, flow ->
            try {
                flow.emit(Resource.Success(data.toModel()))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

    override suspend fun getBookingData(): Flow<Resource<BookingDataModel>> =
        loadResult({ mainService.getBookingData() }, { data, flow ->
            try {
                flow.emit(Resource.Success(data.toModel()))
            } catch (e: Exception) {
                flow.emit(Resource.Error(e.message.toString()))
            }
        })

}