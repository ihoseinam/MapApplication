package ir.hoseinahmadi.mapapplication.data.apiCall

sealed class NetworkResult<out T>(
    val data: T? = null,
    val message: String? = null
) {
    data object Loading : NetworkResult<Nothing>()
    data class Success<out T>(val result: T) : NetworkResult<T>(data = result)
    data class Error(val errorMessage: String) : NetworkResult<Nothing>(message = errorMessage)
}
