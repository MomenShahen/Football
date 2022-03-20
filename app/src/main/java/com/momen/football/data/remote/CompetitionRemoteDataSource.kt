package com.momen.football.data.remote

import com.momen.football.domain.data.response.CompetitionDetailsResponseModel
import com.momen.football.domain.data.response.CompetitionsResponseModel
import com.momen.football.domain.data.response.TeamsResponseModel
import retrofit2.Response

interface CompetitionRemoteDataSource {
    suspend fun getCompetitions(): Response<CompetitionsResponseModel>
    suspend fun getCompetitionDetails(competitionId: Int): Response<CompetitionDetailsResponseModel>
    suspend fun getTeams(id: Int): Response<TeamsResponseModel>
}