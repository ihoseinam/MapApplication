package ir.hoseinahmadi.mapapplication.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherEntity(
    @PrimaryKey val cityName: String,
    val weatherData: String // JSON
)
