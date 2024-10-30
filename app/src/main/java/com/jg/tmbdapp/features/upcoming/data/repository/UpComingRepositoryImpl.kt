package com.jg.tmbdapp.features.upcoming.data.repository

import com.jg.tmbdapp.features.upcoming.data.interfaces.UpComingDataSourceInterface
import com.jg.tmbdapp.features.upcoming.domain.interfaces.UpComingRepositoryInterface
import com.jg.tmbdapp.features.utils.mapper.toModel
import com.jg.tmbdapp.features.utils.models.Movie
import com.jg.tmbdapp.features.utils.models.MovieItem
import com.jg.tmbdapp.features.utils.models.StatusResult
import javax.inject.Inject

class UpComingRepositoryImpl @Inject constructor(private val dataSource: UpComingDataSourceInterface): UpComingRepositoryInterface {
    override suspend fun getUpcomingMovies(): StatusResult<Movie> {
       return when(val response = dataSource.getUpComingMovies()){
            is StatusResult.Error -> StatusResult.Error(response.message)
            is StatusResult.Success -> StatusResult.Success(response.value.toModel())
        }
    }
}