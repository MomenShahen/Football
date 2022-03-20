package com.momen.football.data.remote

import com.momen.football.core.util.CloudConfig
import com.momen.football.domain.data.response.CompetitionDetailsResponseModel
import com.momen.football.domain.data.response.CompetitionsResponseModel
import com.momen.football.domain.data.response.TeamsResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface CompetitionsService {
    @Headers("X-Auth-Token:"+ CloudConfig.API_KEY)
    @GET(CloudConfig.COMPETITIONS)
    suspend fun getCompetitions(): Response<CompetitionsResponseModel>

    @Headers("X-Auth-Token:"+ CloudConfig.API_KEY)
    @GET(CloudConfig.COMPETITION_DETAILS)
    suspend fun getCompetitionDetails(@Path("id") id: Int): Response<CompetitionDetailsResponseModel>

    @Headers("X-Auth-Token:"+ CloudConfig.API_KEY)
    @GET(CloudConfig.TEAMS)
    suspend fun getTeams(@Path("id") id: Int): Response<TeamsResponseModel>
}