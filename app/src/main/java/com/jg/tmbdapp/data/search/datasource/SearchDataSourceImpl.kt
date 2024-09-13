package com.jg.tmbdapp.data.search.datasource

import SearchDataSource
import com.jg.tmbdapp.data.search.model.SearchDTO
import com.jg.tmbdapp.data.utils.network.BaseClient
import com.jg.tmbdapp.data.utils.network.BaseUrl
import com.jg.tmbdapp.domain.utils.StatusResult
import io.ktor.client.call.body

class SearchDataSourceImpl(private val baseClient: BaseClient): SearchDataSource {
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