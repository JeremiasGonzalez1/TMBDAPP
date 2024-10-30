package com.jg.tmbdapp.app.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jg.tmbdapp.features.popular.domain.usecase.PopularUseCase
import com.jg.tmbdapp.features.search.domain.usecase.SearchUseCase
import com.jg.tmbdapp.features.upcoming.domain.usecase.UpComingUseCase
import com.jg.tmbdapp.features.utils.models.Movie
import com.jg.tmbdapp.features.utils.models.StatusResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class HomeUIState<out T> {
    data class Success<out T>(val value: T) : HomeUIState<T>()

    data class Error(val message: String) : HomeUIState<Nothing>()

    data object Loading: HomeUIState<Nothing>()
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularUseCase : PopularUseCase,
    private val searchUseCase: SearchUseCase,
    private val getUpComingMoviesUseCase: UpComingUseCase
) :ViewModel(){

    private val _popularList = MutableStateFlow<HomeUIState<Movie>>(HomeUIState.Loading)
    val popularList = _popularList.asStateFlow()

    private val _searchList = MutableStateFlow<HomeUIState<Movie>>(HomeUIState.Loading)
    val searchList = _searchList.asStateFlow()

    private val _upComingList = MutableStateFlow<HomeUIState<Movie>>(HomeUIState.Loading)
    val upComing = _upComingList.asStateFlow()

    private var job : Job? = null


    fun getPopularMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            when(val result = getPopularUseCase.invoke()){
                is StatusResult.Error ->  _popularList.value = HomeUIState.Error(result.message)
                is StatusResult.Success ->  _popularList.value = HomeUIState.Success(result.value)
            }
        }
    }

    fun searchMovies(query:String){
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
             when(val response = searchUseCase.invoke(query)){
                 is StatusResult.Error -> _searchList.value = HomeUIState.Error(response.message)
                 is StatusResult.Success -> _searchList.value = HomeUIState.Success(response.value)
             }
        }
    }

    fun getUpComingMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            when(val result = getUpComingMoviesUseCase.invoke() ){
                is StatusResult.Error -> _upComingList.value = HomeUIState.Error(result.message)
                is StatusResult.Success -> _upComingList.value = HomeUIState.Success(result.value)
            }
        }
    }
}