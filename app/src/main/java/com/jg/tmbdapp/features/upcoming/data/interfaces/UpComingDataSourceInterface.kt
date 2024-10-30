package com.jg.tmbdapp.features.upcoming.data.interfaces

import com.jg.tmbdapp.features.upcoming.data.model.UpComingDTO
import com.jg.tmbdapp.features.utils.models.StatusResult

interface UpComingDataSourceInterface {
    suspend fun getUpComingMovies(): StatusResult<UpComingDTO>
}