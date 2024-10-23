package com.jg.tmbdapp.features.popular.DI

import com.jg.tmbdapp.features.popular.data.interfaces.PopularDataSource
import com.jg.tmbdapp.features.popular.domain.interfaces.PopularRepository
import com.jg.tmbdapp.features.popular.data.datasource.PopularDatasourceImpl
import com.jg.tmbdapp.features.popular.data.repository.PopularRepositoryImpl
import com.jg.tmbdapp.features.utils.network.BaseClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PopularDI{
    @Provides
    @Singleton
    fun providePopularDataSource(baseClient: BaseClient): PopularDataSource {
        return PopularDatasourceImpl(baseClient)
    }

    @Provides
    @Singleton
    fun provideRepositoryPopular(datasourceImpl: PopularDatasourceImpl): PopularRepository {
        return PopularRepositoryImpl(datasourceImpl)
    }
}