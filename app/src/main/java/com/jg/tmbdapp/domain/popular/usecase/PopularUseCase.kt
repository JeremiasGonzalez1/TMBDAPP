package com.jg.tmbdapp.domain.popular.usecase

import PopularRepository

class PopularUseCase(private val repository : PopularRepository) {

    suspend fun invoke() = repository.getPopularMovies()
}