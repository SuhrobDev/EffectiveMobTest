package dev.soul.data.remote.dto.hotel

import dev.soul.data.remote.dto.hotel.AboutTheHotelDto

data class HotelDto(
    val about_the_hotel: AboutTheHotelDto,
    val adress: String,
    val id: Int,
    val image_urls: List<String>,
    val minimal_price: Int,
    val name: String,
    val price_for_it: String,
    val rating: Int,
    val rating_name: String
)