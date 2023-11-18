package dev.soul.domain.model.hotel

data class HotelModel(
    val about_the_hotel: AboutTheHotelModel,
    val adress: String,
    val id: Int,
    val image_urls: List<String>,
    val minimal_price: Int,
    val name: String,
    val price_for_it: String,
    val rating: Int,
    val rating_name: String
)