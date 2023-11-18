package dev.soul.data.remote

import dev.soul.data.remote.dto.booking.BookingDataDto
import dev.soul.data.remote.dto.hotel.HotelDto
import dev.soul.data.remote.dto.rooms.RoomsHeaderDto
import retrofit2.Response
import retrofit2.http.*

interface MainService {

    @GET("8b532701-709e-4194-a41c-1a903af00195")
    suspend fun getRooms(): Response<RoomsHeaderDto>

    @GET("d144777c-a67f-4e35-867a-cacc3b827473")
    suspend fun getHotel(): Response<HotelDto>

    @GET("63866c74-d593-432c-af8e-f279d1a8d2ff")
    suspend fun getBookingData(): Response<BookingDataDto>

}
