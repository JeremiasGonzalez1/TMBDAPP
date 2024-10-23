package com.jg.tmbdapp.features.popular.data.datasource

import com.jg.tmbdapp.features.popular.data.interfaces.PopularDataSource
import com.jg.tmbdapp.features.utils.network.BaseClient
import com.jg.tmbdapp.features.utils.models.StatusResult
import com.jg.tmbdapp.features.popular.data.model.PopularDTO
import com.jg.tmbdapp.features.utils.network.BaseUrl
import io.ktor.client.call.body
import javax.inject.Inject

class PopularDatasourceImpl @Inject constructor(private val baseClient: BaseClient):
    PopularDataSource {
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