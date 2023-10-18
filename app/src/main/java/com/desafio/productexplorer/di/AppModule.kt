package com.desafio.productexplorer.di

import com.desafio.productexplorer.model.api.PlatziFakeAPI
import com.desafio.productexplorer.model.repository.PlatziFakeRepository
import com.desafio.productexplorer.model.repository.PlatziFakeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .client(OkHttpClient())
            .baseUrl("https://api.escuelajs.co/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    @Singleton
    fun providePlatziFakeAPI(retrofit: Retrofit):PlatziFakeAPI{
        return retrofit.create(PlatziFakeAPI::class.java)
    }

    @Provides
    @Singleton
    fun providePlatzyFakeRepository(apiServices:PlatziFakeAPI): PlatziFakeRepository{
        return PlatziFakeRepositoryImpl(apiServices)
    }
}