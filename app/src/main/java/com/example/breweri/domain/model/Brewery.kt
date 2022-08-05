package com.example.breweri.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.math.roundToInt

@Serializable
data class Brewery(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("brewery_type") val type: String,
    @SerialName("address_2") val address2: String? = null,
    @SerialName("address_3") val address3: String? = null,
    @SerialName("city") val city: String,
    @SerialName("country") val country: String,
    @SerialName("county_province") val countyProvince: String? = null,
    @SerialName("latitude") val latitude: String? = null,
    @SerialName("longitude") val longitude: String? = null,
    @SerialName("phone") val phone: String? = null,
    @SerialName("postal_code") val postalCode: String,
    @SerialName("state") val state: String,
    @SerialName("street") val street: String? = null,
    @SerialName("website_url") val websiteUrl: String? = null,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("rating") val rating: Float? = 0.0f,
    @SerialName("rating_number") val ratingNumber: Int = (Math.random() * 1000).roundToInt(),
) {
    fun display5StarsRating(): Float = (Math.random() * 5f * 10f).roundToInt() / 10f
}
