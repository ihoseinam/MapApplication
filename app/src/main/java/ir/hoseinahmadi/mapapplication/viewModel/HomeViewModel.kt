package ir.hoseinahmadi.mapapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.hoseinahmadi.mapapplication.data.apiCall.NetworkResult
import ir.hoseinahmadi.mapapplication.data.model.WeatherResponse
import ir.hoseinahmadi.mapapplication.repository.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository,
) : ViewModel() {


    private val _cityResponse: MutableStateFlow<NetworkResult<WeatherResponse>> =
        MutableStateFlow(NetworkResult.Loading)
    val cityResponse: StateFlow<NetworkResult<WeatherResponse>> = _cityResponse.asStateFlow()
    fun getWeather(cityName: String) {
        viewModelScope.launch {
            _cityResponse.emit(repository.getWeather(cityName))
        }
    }
}

