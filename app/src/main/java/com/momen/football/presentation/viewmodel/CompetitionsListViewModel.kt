package com.momen.football.presentation.viewmodel

import com.momen.football.core.presentation.viewmodel.NewBaseViewModel
import com.momen.football.core.presentation.viewmodel.ViewEvent
import com.momen.football.domain.usecase.CompetitionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class CompetitionsListViewModel @Inject constructor(
    private val useCase: CompetitionUseCase,
) : NewBaseViewModel<CompetitionState, ViewEvent, CompetitionActions, CompetitionResult>(
    CompetitionState.InitialState) {
    override fun handle(action: CompetitionActions) = flow {
        when (action) {
            is CompetitionActions.InitialState -> emit(CompetitionResult.InitialState)
            is CompetitionActions.LoadCompetitions -> emit(useCase.execute(action))
        }
    }

    override fun reduce(result: CompetitionResult): CompetitionState {
        return when (result) {
            is CompetitionResult.LoadCompetitionsFailure -> CompetitionState.LoadCompetitionsFailure(result.error)
            is CompetitionResult.LoadCompetitionsSuccess -> CompetitionState.LoadCompetitionsSuccess(result.competitionsResponseModel)
            else -> CompetitionState.InitialState
        }
    }
}