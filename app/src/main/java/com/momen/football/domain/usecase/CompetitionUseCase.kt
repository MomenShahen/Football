package com.momen.football.domain.usecase

import com.momen.football.core.domain.usecase.SuspendableUseCase
import com.momen.football.domain.repository.CompetitionRepository
import com.momen.football.presentation.viewmodel.CompetitionActions
import com.momen.football.presentation.viewmodel.CompetitionResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CompetitionUseCase @Inject constructor(private val competitionRepository: CompetitionRepository) :
    SuspendableUseCase<CompetitionActions, CompetitionResult> {
    override suspend fun execute(input: CompetitionActions): CompetitionResult {
        return withContext(Dispatchers.IO) {
            when (input) {
                is CompetitionActions.InitialState -> CompetitionResult.InitialState
                is CompetitionActions.LoadCompetitionDetails -> {
                    val result = competitionRepository.getCompetitionDetails(input.competitionId)
                    if (result.body() != null) {
                        when (result.isSuccessful) {
                            true -> CompetitionResult.LoadCompetitionDetailsSuccess(result.body()!!)
                            else -> CompetitionResult.LoadCompetitionDetailsFailure("Error Loading")
                        }
                    } else {
                        CompetitionResult.LoadCompetitionDetailsFailure("Failure Loading")
                    }
                }
                is CompetitionActions.LoadCompetitions -> {
                    val result = competitionRepository.getCompetitions()
                    if (result.body() != null) {
                        when (result.isSuccessful) {
                            true -> CompetitionResult.LoadCompetitionsSuccess(result.body()!!)
                            else -> CompetitionResult.LoadCompetitionsFailure("Error Loading")
                        }
                    } else {
                        CompetitionResult.LoadCompetitionsFailure("Failure Loading")
                    }
                }
                is CompetitionActions.LoadTeams -> {
                    val result = competitionRepository.getTeams(input.id)
                    if (result.body() != null) {
                        when (result.isSuccessful) {
                            true -> CompetitionResult.LoadTeamsSuccess(result.body()!!)
                            else -> CompetitionResult.LoadTeamsFailure("Error Loading")
                        }
                    } else {
                        CompetitionResult.LoadTeamsFailure("Failure Loading")
                    }
                }
            }

        }
    }
}