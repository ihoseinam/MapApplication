package ir.hoseinahmadi.mapapplication.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import ir.hoseinahmadi.mapapplication.data.db.entity.WeatherEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Upsert
    suspend fun upsertWeather(weather: WeatherEntity)

    @Query("SELECT * FROM weather WHERE cityName = :cityName")
    suspend fun getWeather(cityName: String):WeatherEntity?
}