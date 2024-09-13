package com.jg.tmbdapp.data.popular.datasource

import PopularDataSource
import com.jg.tmbdapp.data.utils.network.BaseClient
import com.jg.tmbdapp.domain.utils.StatusResult
import com.jg.tmbdapp.data.popular.model.PopularDTO
import com.jg.tmbdapp.data.utils.network.BaseUrl
import io.ktor.client.call.body

class PopularDatasourceImpl(private val baseClient: BaseClient):PopularDataSource {
    override suspend fun getPopularMovies(): StatusResult<PopularDTO> {
        val response = baseClient.get(url= BaseUrl.POPULAR, errorMessage = "fallo la peticion")
        try {
            response.httpResponse?.let {
                return StatusResult.Success(it.body())
            }
            return StatusResult.Error(response.errorMessage)
        }catch (e:Exception){
            return StatusResult.Error(e.message.toString())
        }
    }
}