package com.momen.football.presentation.viewmodel

import com.momen.football.core.presentation.viewmodel.NewBaseViewModel
import com.momen.football.core.presentation.viewmodel.ViewEvent
import com.momen.football.domain.usecase.CompetitionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class CompetitionDetailsViewModel @Inject constructor(
    private val useCase: CompetitionUseCase,
) : NewBaseViewModel<CompetitionState, ViewEvent, CompetitionActions, CompetitionResult>(
    CompetitionState.InitialState) {
    override fun handle(action: CompetitionActions) = flow {
        when (action) {
            is CompetitionActions.InitialState -> emit(CompetitionResult.InitialState)
            is CompetitionActions.LoadCompetitionDetails -> emit(useCase.execute(action))
        }
    }

    override fun reduce(result: CompetitionResult): CompetitionState {
        return when (result) {
            is CompetitionResult.LoadCompetitionDetailsFailure -> CompetitionState.LoadCompetitionDetailsFailure(result.error)
            is CompetitionResult.LoadCompetitionDetailsSuccess -> CompetitionState.LoadCompetitionDetailsSuccess(result.competitionDetailsResponseModel)
            else -> CompetitionState.InitialState
        }
    }
}