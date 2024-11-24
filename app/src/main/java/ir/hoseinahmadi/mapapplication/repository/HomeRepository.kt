package ir.hoseinahmadi.mapapplication.repository

import ir.hoseinahmadi.mapapplication.data.apiCall.NetworkResult
import ir.hoseinahmadi.mapapplication.data.db.dao.WeatherDao
import ir.hoseinahmadi.mapapplication.data.model.WeatherResponse
import ir.hoseinahmadi.mapapplication.data.remote.HomeApiInterFace
import ir.hoseinahmadi.mapapplication.util.toEntity
import ir.hoseinahmadi.mapapplication.util.toWeatherResponse
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val homeApiInterFace: HomeApiInterFace,
    private val weatherDao: WeatherDao
) {

    suspend fun getWeather(cityName: String): NetworkResult<WeatherResponse> {
        return try {
            val response = homeApiInterFace.getWeather(cityName)
            if (response.isSuccessful && response.body() != null) {
                val weatherResponse = response.body()!!
                weatherDao.upsertWeather(weatherResponse.toEntity(cityName))
                NetworkResult.Success(weatherResponse)
            } else {
                fetchFromCache(cityName)
            }
        } catch (e: Exception) {
            fetchFromCache(cityName)
        }
    }

    private suspend fun fetchFromCache(cityName: String): NetworkResult<WeatherResponse> {
        val cachedWeather = weatherDao.getWeather(cityName)
        return if (cachedWeather != null) {
            NetworkResult.Success(cachedWeather.toWeatherResponse())
        } else {
            NetworkResult.Error("No cached data available")
        }
    }


}