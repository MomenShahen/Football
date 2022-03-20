package com.momen.football.data.repository

import com.momen.football.data.remote.CompetitionRemoteDataSource
import com.momen.football.domain.data.response.CompetitionDetailsResponseModel
import com.momen.football.domain.data.response.CompetitionsResponseModel
import com.momen.football.domain.data.response.TeamsResponseModel
import com.momen.football.domain.repository.CompetitionRepository
import retrofit2.Response
import javax.inject.Inject

class CompetitionRepositoryImpl @Inject constructor(private val competitionRemoteDataSource: CompetitionRemoteDataSource) :
    CompetitionRepository {
    override suspend fun getCompetitions(): Response<CompetitionsResponseModel> {
        return competitionRemoteDataSource.getCompetitions()
    }

    override suspend fun getCompetitionDetails(competitionId: Int): Response<CompetitionDetailsResponseModel> {
        return competitionRemoteDataSource.getCompetitionDetails(competitionId)
    }

    override suspend fun getTeams(id: Int): Response<TeamsResponseModel> {
        return competitionRemoteDataSource.getTeams(id)
    }
}