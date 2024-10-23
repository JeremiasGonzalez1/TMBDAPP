package com.jg.tmbdapp.features.search.DI

import com.jg.tmbdapp.features.search.data.interfaces.SearchDataSource
import com.jg.tmbdapp.features.search.domain.interfaces.SearchRepository
import com.jg.tmbdapp.features.search.data.datasource.SearchDataSourceImpl
import com.jg.tmbdapp.features.search.data.repository.SearchRepositoryImpl
import com.jg.tmbdapp.features.utils.network.BaseClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchDI{
    @Provides
    @Singleton
    fun provideSearchDataSource(baseClient: BaseClient): SearchDataSource {
        return SearchDataSourceImpl(baseClient)
    }

    @Provides
    @Singleton
    fun provideRepositorySearch(dataSource: SearchDataSource): SearchRepository {
        return SearchRepositoryImpl(dataSource)
    }

}