package com.momen.football.data.remote

import com.momen.football.domain.data.response.CompetitionDetailsResponseModel
import com.momen.football.domain.data.response.CompetitionsResponseModel
import com.momen.football.domain.data.response.TeamsResponseModel
import retrofit2.Response
import javax.inject.Inject

class CompetitionRemoteDataSourceImpl @Inject constructor(private val competitionsService: CompetitionsService) :
    CompetitionRemoteDataSource {
    override suspend fun getCompetitions(): Response<CompetitionsResponseModel> {
        return competitionsService.getCompetitions()
    }

    override suspend fun getCompetitionDetails(competitionId: Int): Response<CompetitionDetailsResponseModel> {
        return competitionsService.getCompetitionDetails(competitionId)
    }

    override suspend fun getTeams(id: Int): Response<TeamsResponseModel> {
        return competitionsService.getTeams(id)
    }
}