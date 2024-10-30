package com.jg.tmbdapp.features.upcoming.domain.usecase

import com.jg.tmbdapp.features.upcoming.domain.interfaces.UpComingRepositoryInterface
import javax.inject.Inject

class UpComingUseCase @Inject constructor(val repository: UpComingRepositoryInterface){

    suspend fun invoke() = repository.getUpcomingMovies()
}