package com.jg.tmbdapp.domain.search.usecase

import com.jg.tmbdapp.domain.search.`interface`.SearchRepository

class SearchUseCase(private val repository: SearchRepository) {
    suspend fun invoke(query:String) = repository.searchMovies(query = query)
}