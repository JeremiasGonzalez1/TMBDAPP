package com.jg.tmbdapp.data.popular.repository

import PopularDataSource
import PopularRepository
import com.jg.tmbdapp.data.mapper.toMap
import com.jg.tmbdapp.domain.popular.model.Popular
import com.jg.tmbdapp.domain.utils.StatusResult

class PopularRepositoryImpl(private val dataSource:PopularDataSource):PopularRepository {
    override suspend fun getPopularMovies(): StatusResult<Popular> {
        return when(val result = dataSource.getPopularMovies()){
            is StatusResult.Error -> StatusResult.Error(result.message)
            is StatusResult.Success -> StatusResult.Success(result.value.toMap())
        }
    }
}