package com.jg.tmbdapp.domain.search.`interface`

import com.jg.tmbdapp.domain.search.model.Search
import com.jg.tmbdapp.domain.utils.StatusResult

interface SearchRepository {
    suspend fun searchMovies(query:String): StatusResult<Search>
}