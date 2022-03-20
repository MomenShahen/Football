package com.momen.football.data.repository

import com.momen.football.data.remote.CompetitionRemoteDataSource
import com.momen.football.domain.data.response.CompetitionDetailsResponseModel
import com.momen.football.domain.data.response.CompetitionsResponseModel
import com.momen.football.domain.data.response.TeamsResponseModel
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
class CompetitionRepositoryImplTest {
    @Mock
    lateinit var competitionRemoteDataSource: CompetitionRemoteDataSource

    lateinit var competitionRepositoryImpl: CompetitionRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        competitionRepositoryImpl = CompetitionRepositoryImpl(competitionRemoteDataSource)
    }

    @Test
    fun `getCompetitions return Success Response`() = runTest {
        val model = CompetitionsResponseModel(1000)
        val expected = Response.success(model)
        Mockito.`when`(competitionRemoteDataSource.getCompetitions()).thenReturn(expected)
        Assert.assertEquals(expected, competitionRepositoryImpl.getCompetitions())
    }
    @Test
    fun `getCompetitionDetails return Success Response`() = runTest{
        val model = CompetitionDetailsResponseModel(1000)
        val expected = Response.success(model)
        Mockito.`when`(competitionRemoteDataSource.getCompetitionDetails(1)).thenReturn(expected)
        Assert.assertEquals(expected, competitionRepositoryImpl.getCompetitionDetails(1))
    }

    @Test
    fun `getTeams return Success Response`() = runTest{
        val model = TeamsResponseModel(1000)
        val expected = Response.success(model)
        Mockito.`when`(competitionRemoteDataSource.getTeams(1)).thenReturn(expected)
        Assert.assertEquals(expected, competitionRepositoryImpl.getTeams(1))
    }
}