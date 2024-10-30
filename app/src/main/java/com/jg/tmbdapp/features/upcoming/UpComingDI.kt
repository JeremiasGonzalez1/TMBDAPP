package com.jg.tmbdapp.features.upcoming

import com.jg.tmbdapp.features.upcoming.data.datasource.UpComingDataSourceImpl
import com.jg.tmbdapp.features.upcoming.data.interfaces.UpComingDataSourceInterface
import com.jg.tmbdapp.features.upcoming.data.repository.UpComingRepositoryImpl
import com.jg.tmbdapp.features.upcoming.domain.interfaces.UpComingRepositoryInterface
import com.jg.tmbdapp.features.utils.network.BaseClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UpComingDI {

    @Provides
    @Singleton
    fun provideUpComingDataSource(baseClient: BaseClient):UpComingDataSourceInterface{
        return UpComingDataSourceImpl(baseClient)
    }

    @Provides
    @Singleton
    fun provideUpComingRepository(upComingDataSource: UpComingDataSourceInterface):UpComingRepositoryInterface{
        return UpComingRepositoryImpl(upComingDataSource)
    }
}