package com.momen.football.presentation.viewmodel

import com.momen.football.core.presentation.viewmodel.Action

sealed class CompetitionActions : Action {
    object InitialState : CompetitionActions()
    object LoadCompetitions : CompetitionActions()
    data class LoadCompetitionDetails(val competitionId: Int) : CompetitionActions()
    data class LoadTeams(val id: Int) : CompetitionActions()

}
