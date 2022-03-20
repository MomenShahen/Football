package com.momen.football.domain.usecase

import com.momen.football.domain.data.response.CompetitionDetailsResponseModel
import com.momen.football.domain.data.response.CompetitionsResponseModel
import com.momen.football.domain.data.response.TeamsResponseModel
import com.momen.football.domain.repository.CompetitionRepository
import com.momen.football.presentation.viewmodel.CompetitionActions
import com.momen.football.presentation.viewmodel.CompetitionResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response


@RunWith(MockitoJUnitRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
class CompetitionUseCaseTest {
    @Mock
    private lateinit var repository: CompetitionRepository
    private lateinit var useCase: CompetitionUseCase

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
        useCase = CompetitionUseCase(repository)
    }
    @Test
    fun `execute takes CompetitionActions LoadCompetitions and return CompetitionResult LoadCompetitionsSuccess`() = runTest{
        val model = CompetitionsResponseModel(1000)
        val expected = Response.success(model)

        Mockito.`when`(repository.getCompetitions()).thenReturn(expected)
        Assert.assertEquals(CompetitionResult.LoadCompetitionsSuccess(expected.body()!!),useCase.execute(CompetitionActions.LoadCompetitions))
    }

    @Test
    fun `execute takes CompetitionActions LoadCompetitionDetails and return CompetitionResult LoadCompetitionDetailsSuccess`() = runTest{
        val model = CompetitionDetailsResponseModel(1000)
        val expected = Response.success(model)

        Mockito.`when`(repository.getCompetitionDetails(1)).thenReturn(expected)
        Assert.assertEquals(CompetitionResult.LoadCompetitionDetailsSuccess(expected.body()!!),useCase.execute(CompetitionActions.LoadCompetitionDetails(1)))
    }

    @Test
    fun `execute takes CompetitionActions LoadTeams and return CompetitionResult LoadTeamsSuccess`() = runTest{
        val model = TeamsResponseModel(1000)
        val expected = Response.success(model)

        Mockito.`when`(repository.getTeams(1)).thenReturn(expected)
        Assert.assertEquals(CompetitionResult.LoadTeamsSuccess(expected.body()!!),useCase.execute(CompetitionActions.LoadTeams(1)))
    }
}