package com.jg.tmbdapp.data.search.repository

import SearchDataSource
import com.jg.tmbdapp.data.utils.mapper.toMap
import com.jg.tmbdapp.domain.search.`interface`.SearchRepository
import com.jg.tmbdapp.domain.search.model.Search
import com.jg.tmbdapp.domain.utils.StatusResult

class SearchRepositoryImpl(private val dataSource:SearchDataSource):SearchRepository {
    override suspend fun searchMovies(query: String): StatusResult<Search> {
        return when(val response = dataSource.searchMovies(query)){
            is StatusResult.Error -> StatusResult.Error(response.message)
            is StatusResult.Success -> StatusResult.Success(response.value.toMap())
        }
    }
}