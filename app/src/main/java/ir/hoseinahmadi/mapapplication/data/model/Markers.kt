package ir.hoseinahmadi.mapapplication.data.model

import com.google.android.gms.maps.model.LatLng

data class Markers(
    val title:String,
    val name:String,
    val latLng :LatLng,
)
