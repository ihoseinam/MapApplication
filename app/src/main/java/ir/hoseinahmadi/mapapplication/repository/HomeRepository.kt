package ir.hoseinahmadi.mapapplication.repository

import ir.hoseinahmadi.mapapplication.data.apiCall.BaseApiResponse
import ir.hoseinahmadi.mapapplication.data.remote.HomeApiInterFace
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val homeApiInterFace: HomeApiInterFace
) : BaseApiResponse() {

    suspend fun getWeather(cityName: String) =
        safeApiCall { homeApiInterFace.getWeather(city = cityName) }

}