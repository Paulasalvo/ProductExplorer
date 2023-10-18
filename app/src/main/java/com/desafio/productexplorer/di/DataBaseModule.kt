package com.desafio.productexplorer.di

import android.content.Context
import androidx.room.Room
import com.desafio.productexplorer.model.database.ProductDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): ProductDataBase {
        return Room.databaseBuilder(context, ProductDataBase::class.java, "product_database").build()
    }
}