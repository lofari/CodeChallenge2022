package com.example.codechallenge

import com.example.codechallenge.api.ApiService
import com.example.codechallenge.api.CharacterRepository
import com.example.codechallenge.api.CharacterRepositoryImpl
import com.example.codechallenge.common.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(): ApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: ApiService): CharacterRepository {
        return CharacterRepositoryImpl(api)
    }
}
