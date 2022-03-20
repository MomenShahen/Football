package com.momen.football.presentation.viewmodel

import com.momen.football.core.presentation.viewmodel.ViewState
import com.momen.football.domain.data.response.CompetitionDetailsResponseModel
import com.momen.football.domain.data.response.CompetitionsResponseModel
import com.momen.football.domain.data.response.TeamsResponseModel

sealed class CompetitionState : ViewState {
    object InitialState : CompetitionState()

    data class LoadCompetitionsSuccess(val competitionsResponseModel: CompetitionsResponseModel) :
        CompetitionState()

    /**
     * @param error can be handled in future with real ErrorModel
     * */
    data class LoadCompetitionsFailure(val error: String) :
        CompetitionState()

    data class LoadCompetitionDetailsSuccess(val competitionDetailsResponseModel: CompetitionDetailsResponseModel) :
        CompetitionState()

    /**
     * @param error can be handled in future with real ErrorModel
     * */
    data class LoadCompetitionDetailsFailure(val error: String) :
        CompetitionState()

    data class LoadTeamsSuccess(val teamsResponseModel: TeamsResponseModel) :
        CompetitionState()

    /**
     * @param error can be handled in future with real ErrorModel
     * */
    data class LoadTeamsFailure(val error: String) :
        CompetitionState()
}
