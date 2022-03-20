package com.momen.football.presentation.viewmodel

import com.momen.football.domain.data.response.CompetitionDetailsResponseModel
import com.momen.football.domain.data.response.CompetitionsResponseModel
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
class CompetitionDetailsViewModelTest {
    @Mock
    private lateinit var useCase: CompetitionUseCase

    private lateinit var competitionDetailsViewModel: CompetitionDetailsViewModel

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
        competitionDetailsViewModel = CompetitionDetailsViewModel(useCase)
    }
    @Test
    fun `handle take CompetitionActions InitialState return CompetitionResult InitialState`() = runTest {
        val action = CompetitionActions.InitialState
        val result = CompetitionResult.InitialState
        val expected = competitionDetailsViewModel.handle(action).first()
        Assert.assertEquals(expected,result)
    }

    @Test
    fun `handle take CompetitionActions LoadCompetitionDetails return CompetitionResult LoadCompetitionDetailsSuccess`() = runTest {
        val action = CompetitionActions.LoadCompetitionDetails(1)
        val result = CompetitionResult.LoadCompetitionDetailsSuccess(
            CompetitionDetailsResponseModel(1))
        lenient().`when`(useCase.execute(action)).thenReturn(result)
        val expected = competitionDetailsViewModel.handle(action).first()
        Assert.assertEquals(expected,result)
    }

    @Test
    fun `handle take CompetitionActions LoadCompetitionDetails return CompetitionResult LoadCompetitionDetailsFailure`() = runTest {
        val action = CompetitionActions.LoadCompetitionDetails(1)
        val result = CompetitionResult.LoadCompetitionDetailsFailure("Error")
        lenient().`when`(useCase.execute(action)).thenReturn(result)
        val expected = competitionDetailsViewModel.handle(action).first()
        Assert.assertEquals(expected,result)
    }
}