package ir.hoseinahmadi.mapapplication.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import ir.hoseinahmadi.mapapplication.data.apiCall.NetworkResult
import ir.hoseinahmadi.mapapplication.data.model.Markers
import ir.hoseinahmadi.mapapplication.ui.component.BaseMarkerItem
import ir.hoseinahmadi.mapapplication.util.Constants
import ir.hoseinahmadi.mapapplication.viewModel.HomeViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MainScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        homeViewModel.getWeather("Tehran")
        homeViewModel.cityResponse.collectLatest { state ->
            when (state) {
                is NetworkResult.Error -> {
                    Log.i("1515","Error :${state.message}" )
                }

                NetworkResult.Loading -> {

                }

                is NetworkResult.Success -> {
                    Log.i("1515", state.data?.main.toString())
                }
            }
        }

    }

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
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        GoogleMap(
            modifier = Modifier
                .fillMaxSize(),
            cameraPositionState = cameraPositionState,
            onMapLoaded = { Log.i("1212", "onMapLoaded") },
        ) {
            markerList.forEach { marks ->
                BaseMarkerItem(marks)
            }
        }
    }

}