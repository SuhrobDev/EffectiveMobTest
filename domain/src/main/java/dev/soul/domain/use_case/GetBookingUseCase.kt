package dev.soul.domain.use_case

import dev.soul.domain.common.Resource
import dev.soul.domain.model.booking.BookingDataModel
import dev.soul.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookingUseCase @Inject constructor(
    private val repository: MainRepository
) {
    suspend fun getBookingData(): Flow<Resource<BookingDataModel>> = repository.getBookingData()
}