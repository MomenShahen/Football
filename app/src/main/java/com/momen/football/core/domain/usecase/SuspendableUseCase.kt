package com.momen.football.core.domain.usecase

interface SuspendableUseCase<I, O> {
    suspend fun execute(input: I): O
}