package com.jg.tmbdapp.features.search.domain.usecase

import com.jg.tmbdapp.features.search.domain.interfaces.SearchRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val repository: SearchRepository) {
    suspend fun invoke(query:String) = repository.searchMovies(query = query)
}