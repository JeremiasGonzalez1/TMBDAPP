package com.jg.tmbdapp.features.search.domain.interfaces

import com.jg.tmbdapp.features.utils.models.Movie
import com.jg.tmbdapp.features.utils.models.StatusResult

interface SearchRepository {
    suspend fun searchMovies(query:String): StatusResult<Movie>
}