import com.jg.tmbdapp.domain.utils.StatusResult
import com.jg.tmbdapp.data.popular.models.PopularDTO

interface PopularDataSource {
    suspend fun getPopularMovies(): StatusResult<PopularDTO>
}