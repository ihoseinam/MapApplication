package ir.hoseinahmadi.mapapplication.util

import com.google.android.gms.maps.model.LatLng
import ir.hoseinahmadi.mapapplication.data.model.Markers

object Constants {
    val IranLatLng = LatLng(32.1675513185197, 54.28540028516086)
    const val BUSE_URL ="https://api.openweathermap.org/data/2.5/"
    const val WEATHER_API_KEY ="b3a33071ab2bfcc5371cec9b6dcd0197"
    const val DATABASE_NAME="my_db"
    val default_markers = listOf(
        Markers(
            title = "Isfahan",
            name = "",
            latLng = LatLng(32.66333690292618, 51.66984269930433)
        ),
        Markers(
            title = "Qom",
            name = "",
            latLng = LatLng(34.67844135409665, 51.102671589834245)
        ),
        Markers(
            title = "Chalus",
            name = "",
            latLng = LatLng(36.61427924291714, 51.58840774352295)
        ),
        Markers(
            title = "Tabas",
            name = "",
            latLng = LatLng(33.66743875056987, 57.165455394849)
        ),
             Markers(
            title = "Karaj",
            name = "",
            latLng = LatLng(35.94806719145514, 50.659912382107564)
        ),

    )
}