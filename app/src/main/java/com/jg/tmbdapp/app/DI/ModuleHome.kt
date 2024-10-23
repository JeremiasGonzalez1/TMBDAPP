package com.jg.tmbdapp.app.DI

import com.jg.tmbdapp.features.popular.domain.interfaces.PopularRepository
import com.jg.tmbdapp.features.search.domain.interfaces.SearchRepository
import com.jg.tmbdapp.features.popular.domain.usecase.PopularUseCase
import com.jg.tmbdapp.features.search.domain.usecase.SearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule{

    @Provides
    @Singleton
    fun provideUseCasePopular(repository: PopularRepository): PopularUseCase {
        return PopularUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideUseCaseSearch(repository: SearchRepository): SearchUseCase {
        return SearchUseCase(repository)
    }
}

//val moduleHome = module {
//    single<BaseClient>{
//        val baseClient = HttpClient(CIO) {
//            install(ContentNegotiation) {
//                json(
//                    json = Json {
//                        ignoreUnknownKeys = true
//                        prettyPrint = true
//                        isLenient = true
//                    }
//                )
//            }
//        }
//        BaseClient(baseClient)
//    }
//
//    //PopularMovies
//    factory<com.jg.tmbdapp.features.popular.data.interfaces.PopularDataSource>{PopularDatasourceImpl(get())}
//    factory<com.jg.tmbdapp.features.popular.domain.interfaces.PopularRepository> { PopularRepositoryImpl(get()) }
//    factory<PopularUseCase>{ PopularUseCase(get()) }
//
//    //SearchMovies
//    factory<com.jg.tmbdapp.features.search.data.interfaces.SearchDataSource>{ SearchDataSourceImpl(get()) }
//    factory<com.jg.tmbdapp.features.search.domain.interfaces.SearchRepository> { SearchRepositoryImpl(get()) }
//    factory<SearchUseCase>{ SearchUseCase(get()) }
//}
//
//val moduleHomeViewModel = module{
//    factory<HomeViewModel>{HomeViewModel(get(), get())}
//}