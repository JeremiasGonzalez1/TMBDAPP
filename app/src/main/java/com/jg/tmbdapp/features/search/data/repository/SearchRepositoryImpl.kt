package com.jg.tmbdapp.features.search.data.repository

import com.jg.tmbdapp.features.search.data.interfaces.SearchDataSource
import com.jg.tmbdapp.features.search.domain.interfaces.SearchRepository
import com.jg.tmbdapp.features.utils.mapper.toMap
import com.jg.tmbdapp.features.search.domain.model.Search
import com.jg.tmbdapp.features.utils.models.StatusResult
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val dataSource: SearchDataSource):
    SearchRepository {
    override suspend fun searchMovies(query: String): StatusResult<Search> {
        return when(val response = dataSource.searchMovies(query)){
            is StatusResult.Error -> StatusResult.Error(response.message)
            is StatusResult.Success -> StatusResult.Success(response.value.toMap())
        }
    }
}