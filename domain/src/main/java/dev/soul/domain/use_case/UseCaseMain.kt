package dev.soul.domain.use_case

import javax.inject.Inject

data class UseCaseMain @Inject constructor(
    val roomUseCase: GetRoomUseCase,
    val bookingUseCase: GetBookingUseCase,
    val hotelUseCase: GetHotelUseCase
)
