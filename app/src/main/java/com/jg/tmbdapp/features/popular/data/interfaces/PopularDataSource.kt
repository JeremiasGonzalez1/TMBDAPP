package com.jg.tmbdapp.features.popular.data.interfaces

import com.jg.tmbdapp.features.utils.models.StatusResult
import com.jg.tmbdapp.features.popular.data.model.PopularDTO

interface PopularDataSource {
    suspend fun getPopularMovies(): StatusResult<PopularDTO>
}