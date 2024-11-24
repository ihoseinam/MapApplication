package ir.hoseinahmadi.mapapplication.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.hoseinahmadi.mapapplication.data.remote.HomeApiInterFace
import ir.hoseinahmadi.mapapplication.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetWorkModule {

    @Provides
    @Singleton
    fun provideNetworkModule(): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BUSE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideHomeApiInterFace(retrofit: Retrofit): HomeApiInterFace =
        retrofit.create(HomeApiInterFace::class.java)
}