package com.fottow.fottow.di

import android.content.Context
import com.fottow.fottow.FottowApplication.Companion.app
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppContext(): Context = app
}