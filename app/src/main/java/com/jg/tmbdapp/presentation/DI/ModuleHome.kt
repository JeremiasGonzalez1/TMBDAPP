package com.jg.tmbdapp.presentation.DI

import PopularDataSource
import PopularRepository
import SearchDataSource
import com.jg.tmbdapp.data.utils.network.BaseClient
import com.jg.tmbdapp.data.popular.datasource.PopularDatasourceImpl
import com.jg.tmbdapp.data.popular.repository.PopularRepositoryImpl
import com.jg.tmbdapp.data.search.datasource.SearchDataSourceImpl
import com.jg.tmbdapp.data.search.repository.SearchRepositoryImpl
import com.jg.tmbdapp.domain.popular.usecase.PopularUseCase
import com.jg.tmbdapp.domain.search.`interface`.SearchRepository
import com.jg.tmbdapp.domain.search.usecase.SearchUseCase
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

    //PopularMovies
    factory<PopularDataSource>{PopularDatasourceImpl(get())}
    factory<PopularRepository> { PopularRepositoryImpl(get()) }
    factory<PopularUseCase>{ PopularUseCase(get()) }

    //SearchMovies
    factory<SearchDataSource>{ SearchDataSourceImpl(get()) }
    factory<SearchRepository> { SearchRepositoryImpl(get()) }
    factory<SearchUseCase>{ SearchUseCase(get()) }
}

val moduleHomeViewModel = module{
    factory<HomeViewModel>{HomeViewModel(get(), get())}
}