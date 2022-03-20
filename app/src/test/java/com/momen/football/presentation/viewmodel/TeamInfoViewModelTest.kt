package com.momen.football.presentation.viewmodel

import com.momen.football.domain.data.response.TeamsResponseModel
import com.momen.football.domain.usecase.CompetitionUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.lenient
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
class TeamInfoViewModelTest {
    @Mock
    private lateinit var useCase: CompetitionUseCase

    private lateinit var teamInfoViewModel: TeamInfoViewModel

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
        teamInfoViewModel = TeamInfoViewModel(useCase)
    }
    @Test
    fun `handle take CompetitionActions InitialState return CompetitionResult InitialState`() = runTest {
        val action = CompetitionActions.InitialState
        val result = CompetitionResult.InitialState
        val expected = teamInfoViewModel.handle(action).first()
        Assert.assertEquals(expected,result)
    }

    @Test
    fun `handle take CompetitionActions LoadTeams return CompetitionResult LoadTeamsSuccess`() = runTest {
        val action = CompetitionActions.LoadTeams(1)
        val result = CompetitionResult.LoadTeamsSuccess(
            TeamsResponseModel(1111))
        lenient().`when`(useCase.execute(action)).thenReturn(result)
        val expected = teamInfoViewModel.handle(action).first()
        Assert.assertEquals(expected,result)
    }

    @Test
    fun `handle take CompetitionActions LoadTeams return CompetitionResult LoadTeamsFailure`() = runTest {
        val action = CompetitionActions.LoadTeams(1)
        val result = CompetitionResult.LoadTeamsFailure("Error")
        lenient().`when`(useCase.execute(action)).thenReturn(result)
        val expected = teamInfoViewModel.handle(action).first()
        Assert.assertEquals(expected,result)
    }
}