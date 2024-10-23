package com.jg.tmbdapp.features.popular.data.repository

import com.jg.tmbdapp.features.popular.data.interfaces.PopularDataSource
import com.jg.tmbdapp.features.popular.domain.interfaces.PopularRepository
import com.jg.tmbdapp.features.utils.mapper.toMap
import com.jg.tmbdapp.features.popular.domain.model.Popular
import com.jg.tmbdapp.features.utils.models.StatusResult
import javax.inject.Inject

class PopularRepositoryImpl @Inject constructor(private val dataSource: PopularDataSource):
    PopularRepository {
    override suspend fun getPopularMovies(): StatusResult<Popular> {
        return when(val result = dataSource.getPopularMovies()){
            is StatusResult.Error -> StatusResult.Error(result.message)
            is StatusResult.Success -> StatusResult.Success(result.value.toMap())
        }
    }
}