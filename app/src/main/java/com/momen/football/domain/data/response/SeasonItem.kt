package com.momen.football.domain.data.response

data class SeasonItem(
    val id: Int,
    val startDate: String?,
    val endDate: String?,
    val currentMatchday: String?,
    val winner: Winner?
)
