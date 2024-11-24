package ir.hoseinahmadi.mapapplication.data.apiCall

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

abstract class BaseApiResponse {

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> =
        withContext(Dispatchers.IO) {
            try {
                val response = apiCall()
                if (response.isSuccessful) {
                   response.body()?.let {res->
                       return@withContext NetworkResult.Success(res)
                   }
                }
                return@withContext NetworkResult.Error("Error:${response.message()}")
            } catch (e: Exception) {
                return@withContext NetworkResult.Error(e.localizedMessage?:"error")
            }
        }
}
