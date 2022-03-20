package com.momen.football.domain.data.response

data class CompetitionDetailsResponseModel(
    val id: Int,
    val area: Area? = null,
    val name: String? = null,
    val code: String? = null,
    val emblemUrl: String? = null,
    val plan: String? = null,
    val currentSeason: SeasonItem? = null,
    val seasons:ArrayList<SeasonItem>? = null,
    val lastUpdated:String? = null
)
