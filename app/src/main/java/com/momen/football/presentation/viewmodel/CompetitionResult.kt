package com.momen.football.presentation.viewmodel

import com.momen.football.core.presentation.viewmodel.Result
import com.momen.football.domain.data.response.CompetitionDetailsResponseModel
import com.momen.football.domain.data.response.CompetitionsResponseModel
import com.momen.football.domain.data.response.TeamsResponseModel

sealed class CompetitionResult : Result {
    object InitialState : CompetitionResult()

    data class LoadCompetitionsSuccess(val competitionsResponseModel: CompetitionsResponseModel) :
        CompetitionResult()

    /**
     * @param error can be handled in future with real ErrorModel
     * */
    data class LoadCompetitionsFailure(val error: String) :
        CompetitionResult()

    data class LoadCompetitionDetailsSuccess(val competitionDetailsResponseModel: CompetitionDetailsResponseModel) :
        CompetitionResult()

    /**
     * @param error can be handled in future with real ErrorModel
     * */
    data class LoadCompetitionDetailsFailure(val error: String) :
        CompetitionResult()

    data class LoadTeamsSuccess(val teamsResponseModel: TeamsResponseModel) :
        CompetitionResult()

    /**
     * @param error can be handled in future with real ErrorModel
     * */
    data class LoadTeamsFailure(val error: String) :
        CompetitionResult()
}
