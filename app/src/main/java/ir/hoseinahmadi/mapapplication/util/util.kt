package ir.hoseinahmadi.mapapplication.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.google.gson.Gson
import ir.hoseinahmadi.mapapplication.data.db.entity.WeatherEntity
import ir.hoseinahmadi.mapapplication.data.model.WeatherResponse
import kotlin.math.roundToInt

fun WeatherResponse.toEntity(cityName: String): WeatherEntity {
    val json = Gson().toJson(this)
    return WeatherEntity(cityName = cityName, weatherData = json)
}

fun WeatherEntity.toWeatherResponse(): WeatherResponse {
    return Gson().fromJson(this.weatherData, WeatherResponse::class.java)
}

fun Double.convertKelvinToCelsius():String{
    return (this - 273.15).roundToInt().toString()
}

fun checkNetworkConnection(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return false
    val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
    return when {
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        else -> false
    }
}