package com.momen.football.domain.data.response

data class TeamsResponseModel(
    val count: Int,
    val filters: Filter? = null,
    val competitions: ArrayList<CompetitionItem>? = null,
    val season:SeasonItem? = null,
    val teams:ArrayList<Team>? = null,
)
