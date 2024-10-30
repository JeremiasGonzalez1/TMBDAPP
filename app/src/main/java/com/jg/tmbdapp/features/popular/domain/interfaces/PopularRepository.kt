package com.jg.tmbdapp.features.popular.domain.interfaces

import com.jg.tmbdapp.features.utils.models.Movie
import com.jg.tmbdapp.features.utils.models.StatusResult

interface PopularRepository {
    suspend fun getPopularMovies(): StatusResult<Movie>
}