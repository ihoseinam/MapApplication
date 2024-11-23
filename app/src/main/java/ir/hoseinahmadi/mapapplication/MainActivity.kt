package ir.hoseinahmadi.mapapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerComposable
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import dagger.hilt.android.AndroidEntryPoint
import ir.hoseinahmadi.mapapplication.ui.screens.MainScreen
import ir.hoseinahmadi.mapapplication.ui.theme.MapApplicationTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MapApplicationTheme {
                MainScreen()
            }
        }
    }
}



@SuppressLint("UnrememberedMutableState")
@Composable
fun Map() {
    val lahoreMap = LatLng(32.1675513185197, 54.28540028516086)
    val singaporeMarkerState = rememberMarkerState(position = lahoreMap)
    val isfehanMarker = rememberMarkerState(position = LatLng(32.48319717812227, 52.19799815687525))
    val tehranMarker = rememberMarkerState(position = LatLng(35.774444433308645, 51.297119343615144))
    val tabasMarker = rememberMarkerState(position = LatLng(33.62498612463618, 56.96606407095922))
    val sariMarker = rememberMarkerState(position = LatLng(36.62551936050763, 53.428466779864664))
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(lahoreMap, 6f)
    }
    var uiSettings by remember {
        mutableStateOf(MapUiSettings(zoomControlsEnabled = true))
    }
    var properties by remember {
        mutableStateOf(MapProperties(mapType = MapType.NORMAL))
    }
    /*
    * انواع حالت‌های نقشه:
    mapType در MapProperties می‌تواند یکی از مقادیر زیر باشد:

    MapType.NORMAL: نمای عادی نقشه (پیش‌فرض).
    MapType.SATELLITE: نمای ماهواره‌ای.
    MapType.HYBRID: ترکیبی از ماهواره‌ای و عادی.
    MapType.TERRAIN: نمایش توپوگرافی زمین.
    MapType.NONE: بدون نمایش پس‌زمینه نقشه.
    * */
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = properties,
        uiSettings = uiSettings,
        onMapLoaded = { Log.i("1212","onMapLoaded") },
    ) {
        Marker(
            state = isfehanMarker,
            title = "isfhan",
            snippet = "Marker in Singapore",
        )

     /*   MarkerComposable(
            state = singaporeMarkerState,
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Star Icon",
                tint = Color.Blue,
                modifier = Modifier.size(58.dp)
            )
        }*/
    }


}