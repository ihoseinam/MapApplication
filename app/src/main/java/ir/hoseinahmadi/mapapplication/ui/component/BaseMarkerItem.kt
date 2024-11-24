package ir.hoseinahmadi.mapapplication.ui.component

import androidx.compose.runtime.Composable
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberMarkerState
import ir.hoseinahmadi.mapapplication.data.model.Markers

@Composable
fun BaseMarkerItem(item:Markers){
    Marker(
        title = item.title,
        state = rememberMarkerState(position = item.latLng),
        /*       onClick = {
                   Toast.makeText(context, it.title, Toast.LENGTH_SHORT).show()
                   true
               }*/
    )
}