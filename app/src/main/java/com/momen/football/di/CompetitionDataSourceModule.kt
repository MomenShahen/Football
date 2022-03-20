package com.momen.football.di

import com.momen.football.data.remote.CompetitionRemoteDataSource
import com.momen.football.data.remote.CompetitionRemoteDataSourceImpl
import com.momen.football.data.repository.CompetitionRepositoryImpl
import com.momen.football.domain.repository.CompetitionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class CompetitionDataSourceModule {
    @Binds
    abstract fun bindCompetitionRemoteDataSource(competitionRemoteDataSourceImpl: CompetitionRemoteDataSourceImpl): CompetitionRemoteDataSource

    @Binds
    abstract fun bindCompetitionRepository(competitionRepositoryImpl: CompetitionRepositoryImpl): CompetitionRepository
}