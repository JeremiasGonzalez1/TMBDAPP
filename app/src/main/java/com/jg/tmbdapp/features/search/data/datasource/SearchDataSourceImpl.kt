package com.jg.tmbdapp.features.search.data.datasource

import com.jg.tmbdapp.features.search.data.interfaces.SearchDataSource
import com.jg.tmbdapp.features.search.data.model.SearchDTO
import com.jg.tmbdapp.features.utils.network.BaseClient
import com.jg.tmbdapp.features.utils.network.BaseUrl
import com.jg.tmbdapp.features.utils.models.StatusResult
import io.ktor.client.call.body
import javax.inject.Inject

class SearchDataSourceImpl @Inject constructor(private val baseClient: BaseClient): SearchDataSource {
    override suspend fun searchMovies(query: String): StatusResult<SearchDTO> {
        val response = baseClient.get(BaseUrl.SEARCH, query = query, "Fallo al obtener buscados")
         try{
             response.httpResponse?.let {
                 return StatusResult.Success(it.body())
             }
             return StatusResult.Error(response.errorMessage)
        }catch (e:Exception){
            return StatusResult.Error(response.errorMessage)
        }
    }
}