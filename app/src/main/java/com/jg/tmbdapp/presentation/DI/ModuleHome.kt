package com.jg.tmbdapp.presentation.DI

import PopularDataSource
import PopularRepository
import com.jg.tmbdapp.data.network.BaseClient
import com.jg.tmbdapp.data.popular.datasource.PopularDatasourceImpl
import com.jg.tmbdapp.data.popular.repository.PopularRepositoryImpl
import com.jg.tmbdapp.domain.popular.usecase.PopularUseCase
import com.jg.tmbdapp.presentation.screens.HomeViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val moduleHome = module {
    single<BaseClient>{
        val baseClient = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                        isLenient = true
                    }
                )
            }
        }
        BaseClient(baseClient)
    }

    factory<PopularDataSource>{PopularDatasourceImpl(get())}
    factory<PopularRepository> { PopularRepositoryImpl(get()) }
    factory<PopularUseCase>{ PopularUseCase(get()) }
}

val moduleHomeViewModel = module{
    factory<HomeViewModel>{HomeViewModel(get())}
}