package ir.hoseinahmadi.mapapplication.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.hoseinahmadi.mapapplication.data.db.dao.WeatherDao
import ir.hoseinahmadi.mapapplication.data.db.entity.WeatherEntity

@Database(entities = [WeatherEntity::class], exportSchema = false, version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun WeatherDao(): WeatherDao
}