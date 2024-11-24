package ir.hoseinahmadi.mapapplication.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import ir.hoseinahmadi.mapapplication.data.model.CityDetail
import ir.hoseinahmadi.mapapplication.ui.component.BaseMarkerItem
import ir.hoseinahmadi.mapapplication.ui.component.DialogCityDetail
import ir.hoseinahmadi.mapapplication.util.Constants
import ir.hoseinahmadi.mapapplication.util.checkNetworkConnection
import ir.hoseinahmadi.mapapplication.viewModel.HomeViewModel

@Composable
fun MainScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val markerList = remember { Constants.default_markers }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(Constants.IranLatLng, 6f)
    }
    var cityDetail by remember { mutableStateOf<CityDetail?>(null) }
    DialogCityDetail(
        cityDetail = cityDetail,
        onDismissRequest = { cityDetail = null },
        homeViewModel = homeViewModel,
    )
    LaunchedEffect(true) {
        if (!checkNetworkConnection(context)) {
            Toast.makeText(context, "عدم اتصال اینترنت", Toast.LENGTH_SHORT).show()
        }
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        GoogleMap(
            modifier = Modifier
                .fillMaxSize(),
            uiSettings = MapUiSettings(zoomControlsEnabled = false),
            cameraPositionState = cameraPositionState,
        ) {
            markerList.forEach { marks ->
                BaseMarkerItem(marks) {
                    cityDetail = CityDetail(name = marks.title, isShoeDialog = true)
                }
            }
        }
    }

}