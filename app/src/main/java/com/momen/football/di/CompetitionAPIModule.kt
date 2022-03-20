package com.momen.football.di

import com.momen.football.data.remote.CompetitionsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CompetitionAPIModule {
    @Provides
    @Singleton
    fun provideCompetitionService(retrofit: Retrofit) = retrofit.create(CompetitionsService::class.java)

}