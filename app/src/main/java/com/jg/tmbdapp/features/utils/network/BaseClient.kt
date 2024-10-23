package com.jg.tmbdapp.features.utils.network

import com.jg.tmbdapp.BuildConfig.token
import com.jg.tmbdapp.features.utils.network.BaseUrl.BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BaseClient @Inject constructor(private val httpClient: HttpClient) {

    internal suspend fun get(url:String, query:String? = null, errorMessage:String): HttpStatus {
        return try {
            val response = httpClient.get(BASE_URL + url) {
                contentType(ContentType.Application.Json)
                header(
                    "Authorization",
                    "Bearer $token"
                )
                if (query!=null){
                parameter("query", query)
                }
            }
            if (response.status.value in 200..299)
                HttpStatus(httpResponse = response)
            else
                HttpStatus(errorMessage = errorMessage)
        } catch (e: Exception) {
            HttpStatus(errorMessage = "Ups! Atrapaste un error desconocido, vuelve a intentarlo")
        }
    }
}
internal data class HttpStatus(
    val httpResponse: HttpResponse? = null,
    val errorMessage: String = "",
)