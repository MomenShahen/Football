package com.momen.football.data.remote

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
class CompetitionRemoteDataSourceImplTest {
    @Mock
    lateinit var competitionsService: CompetitionsService

    private lateinit var competitionRemoteDataSourceImpl: CompetitionRemoteDataSourceImpl

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
        competitionRemoteDataSourceImpl = CompetitionRemoteDataSourceImpl(competitionsService)
    }
    @Test
    fun `getCompetitions return Success Response`() = runTest{
        val model = CompetitionsResponseModel(1000)
        val expected = Response.success(model)
        Mockito.`when`(competitionsService.getCompetitions()).thenReturn(expected)
        Assert.assertEquals(expected, competitionRemoteDataSourceImpl.getCompetitions())
    }

    @Test
    fun `getCompetitionDetails return Success Response`() = runTest{
        val model = CompetitionDetailsResponseModel(1000)
        val expected = Response.success(model)
        Mockito.`when`(competitionsService.getCompetitionDetails(1)).thenReturn(expected)
        Assert.assertEquals(expected, competitionRemoteDataSourceImpl.getCompetitionDetails(1))
    }

    @Test
    fun `getTeams return Success Response`() = runTest{
        val model = TeamsResponseModel(1000)
        val expected = Response.success(model)
        Mockito.`when`(competitionsService.getTeams(1)).thenReturn(expected)
        Assert.assertEquals(expected, competitionRemoteDataSourceImpl.getTeams(1))
    }
}