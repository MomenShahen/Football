package com.momen.football.domain.data.response

data class CompetitionsResponseModel(
    val count: Int,
    val filters: Filter? = null,
    val competition: ArrayList<CompetitionItem>? = null
)
