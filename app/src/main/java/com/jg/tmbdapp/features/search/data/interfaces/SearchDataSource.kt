package com.jg.tmbdapp.features.search.data.interfaces

import com.jg.tmbdapp.features.search.data.model.SearchDTO
import com.jg.tmbdapp.features.utils.models.StatusResult

interface SearchDataSource {
    suspend fun searchMovies(query:String): StatusResult<SearchDTO>
}