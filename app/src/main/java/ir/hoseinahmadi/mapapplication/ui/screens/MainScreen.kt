package ir.hoseinahmadi.mapapplication.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.maps.LocationSource
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import ir.hoseinahmadi.mapapplication.data.model.Markers
import ir.hoseinahmadi.mapapplication.util.Constants

@Composable
fun MainScreen() {

    val markerList = listOf(
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
    )

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(Constants.IranLatLng, 6f)
    }
    val context = LocalContext.current
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        onMapLoaded = { Log.i("1212","onMapLoaded") },
    ) {
        markerList.forEach {marks->
            Marker(
                title = marks.title,
                state = rememberMarkerState(position =marks.latLng),
         /*       onClick = {
                    Toast.makeText(context, it.title, Toast.LENGTH_SHORT).show()
                    true
                }*/
            )
        }
    }
}