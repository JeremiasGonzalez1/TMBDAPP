package com.jg.tmbdapp.features.popular.domain.usecase

import com.jg.tmbdapp.features.popular.domain.interfaces.PopularRepository
import javax.inject.Inject

class PopularUseCase @Inject constructor(private val repository : PopularRepository) {

    suspend fun invoke() = repository.getPopularMovies()
}