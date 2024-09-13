import com.jg.tmbdapp.domain.utils.StatusResult
import com.jg.tmbdapp.data.popular.model.PopularDTO

interface PopularDataSource {
    suspend fun getPopularMovies(): StatusResult<PopularDTO>
}