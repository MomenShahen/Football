package com.momen.football.domain.data.response

data class CompetitionItem(
    val id: Int,
    val area: Area?,
    val name: String?,
    val code: String?,
    val emblemUrl: String?,
    val plan: String?,
    val currentSeason: SeasonItem?,
    val numberOfAvailableSeasons:Int?,
    val lastUpdated:String?
)
