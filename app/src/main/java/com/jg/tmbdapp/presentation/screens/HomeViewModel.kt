package com.jg.tmbdapp.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jg.tmbdapp.domain.popular.model.Popular
import com.jg.tmbdapp.domain.popular.usecase.PopularUseCase
import com.jg.tmbdapp.domain.utils.StatusResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class HomeUIState<out T> {
    data class Success<out T>(val value: T) : HomeUIState<T>()

    data class Error(val message: String) : HomeUIState<Nothing>()

    data class Loading(val status:Boolean): HomeUIState<Nothing>()
}


class HomeViewModel(private val getPopularUseCase : PopularUseCase) :ViewModel(){
    private val _popularList = MutableStateFlow<HomeUIState<Popular>>(HomeUIState.Loading(true))
    val popularList = _popularList.asStateFlow()


    fun getPopularMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            when(val result = getPopularUseCase.invoke()){
                is StatusResult.Error ->  _popularList.value = HomeUIState.Error(result.message)
                is StatusResult.Success ->  _popularList.value = HomeUIState.Success(result.value)
            }
        }
    }
}