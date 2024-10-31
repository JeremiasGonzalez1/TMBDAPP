package com.jg.tmbdapp.features.upcoming.domain.interfaces

import com.jg.tmbdapp.features.utils.models.Movie
import com.jg.tmbdapp.features.utils.models.StatusResult

interface UpComingRepositoryInterface {
    suspend fun getUpcomingMovies(): StatusResult<Movie>
}