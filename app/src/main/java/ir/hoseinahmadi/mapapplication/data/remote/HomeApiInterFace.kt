package ir.hoseinahmadi.mapapplication.data.remote

import ir.hoseinahmadi.mapapplication.data.model.WeatherResponse
import ir.hoseinahmadi.mapapplication.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApiInterFace {

    @GET("weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String=Constants.WEATHER_API_KEY,
        @Query("units") units: String = "metric"
    ):Response<WeatherResponse>

}