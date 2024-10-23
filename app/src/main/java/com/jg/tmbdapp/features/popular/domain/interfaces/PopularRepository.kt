package com.jg.tmbdapp.features.popular.domain.interfaces

import com.jg.tmbdapp.features.popular.domain.model.Popular
import com.jg.tmbdapp.features.utils.models.StatusResult

interface PopularRepository {
    suspend fun getPopularMovies(): StatusResult<Popular>
}