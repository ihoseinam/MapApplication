package ir.hoseinahmadi.mapapplication.ui.component

import android.widget.Toast
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import ir.hoseinahmadi.mapapplication.data.apiCall.NetworkResult
import ir.hoseinahmadi.mapapplication.data.model.CityDetail
import ir.hoseinahmadi.mapapplication.viewModel.HomeViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DialogCityDetail(
    cityDetail: CityDetail?,
    onDismissRequest: () -> Unit,
    homeViewModel: HomeViewModel,
) {
    if (cityDetail == null || !cityDetail.isShoeDialog) return
    val context = LocalContext.current
    var temperature by remember { mutableStateOf<Double?>(null) }
    var serverLoading by remember { mutableStateOf(true) }
    LaunchedEffect(Unit) {
        homeViewModel.getWeather(cityDetail.name)
        homeViewModel.cityResponse.collectLatest { state ->
            when (state) {
                is NetworkResult.Error -> {
                    serverLoading =false
                    Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
                }

                NetworkResult.Loading -> {

                }

                is NetworkResult.Success -> {
                    state.data?.let { res ->
                        temperature = res.main.temp
                        serverLoading = false
                    }
                }
            }
        }
    }
        AlertDialog(
            onDismissRequest = onDismissRequest,
            title = {
                Text(
                    text = "دمای هوای شهر:\n${cityDetail.name}",
                    fontSize = 18.sp
                )
            },
            text = {
                if (serverLoading){
                    CircularProgressIndicator()
                }else{
                    Text(
                        text = "${temperature?:"0"} سانتی گراد ",
                        fontSize = 18.sp
                    )
                }
            },
            confirmButton = {},
        )
    }
