package com.jg.tmbdapp.features.upcoming.data.datasource

import com.jg.tmbdapp.features.upcoming.data.interfaces.UpComingDataSourceInterface
import com.jg.tmbdapp.features.upcoming.data.model.UpComingDTO
import com.jg.tmbdapp.features.utils.models.StatusResult
import com.jg.tmbdapp.features.utils.network.BaseClient
import com.jg.tmbdapp.features.utils.network.BaseUrl
import io.ktor.client.call.body
import javax.inject.Inject

class UpComingDataSourceImpl @Inject constructor(val baseClient: BaseClient):UpComingDataSourceInterface {
    override suspend fun getUpComingMovies(): StatusResult<UpComingDTO> {
        val response = baseClient.get(BaseUrl.UP_COMING, errorMessage = "fail to request up-coming movies")
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