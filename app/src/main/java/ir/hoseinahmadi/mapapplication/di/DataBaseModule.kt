package ir.hoseinahmadi.mapapplication.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.hoseinahmadi.mapapplication.data.db.AppDataBase
import ir.hoseinahmadi.mapapplication.data.db.dao.WeatherDao
import ir.hoseinahmadi.mapapplication.util.Constants
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataBaseModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): AppDataBase =
        Room.databaseBuilder(
            context = context,
            klass = AppDataBase::class.java,
            name = Constants.DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun provideWeatherDao(database: AppDataBase): WeatherDao = database.WeatherDao()

}