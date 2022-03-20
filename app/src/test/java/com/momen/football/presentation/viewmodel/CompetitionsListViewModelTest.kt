package com.momen.football.presentation.viewmodel

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
class CompetitionsListViewModelTest {
    @Mock
    private lateinit var useCase: CompetitionUseCase

    private lateinit var competitionsListViewModel: CompetitionsListViewModel

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
        competitionsListViewModel = CompetitionsListViewModel(useCase)
    }
    @Test
    fun `handle take CompetitionActions InitialState return CompetitionResult InitialState`() = runTest {
        val action = CompetitionActions.InitialState
        val result = CompetitionResult.InitialState
        val expected = competitionsListViewModel.handle(action).first()
        Assert.assertEquals(expected,result)
    }

    @Test
    fun `handle take CompetitionActions LoadCompetitions return CompetitionResult LoadCompetitionsSuccess`() = runTest {
        val action = CompetitionActions.LoadCompetitions
        val result = CompetitionResult.LoadCompetitionsSuccess(
            CompetitionsResponseModel(1111))
        lenient().`when`(useCase.execute(action)).thenReturn(result)
        val expected = competitionsListViewModel.handle(action).first()
        Assert.assertEquals(expected,result)
    }

    @Test
    fun `handle take CompetitionActions LoadCompetitions return CompetitionResult LoadCompetitionsFailure`() = runTest {
        val action = CompetitionActions.LoadCompetitions
        val result = CompetitionResult.LoadCompetitionsFailure("Error")
        lenient().`when`(useCase.execute(action)).thenReturn(result)
        val expected = competitionsListViewModel.handle(action).first()
        Assert.assertEquals(expected,result)
    }
}