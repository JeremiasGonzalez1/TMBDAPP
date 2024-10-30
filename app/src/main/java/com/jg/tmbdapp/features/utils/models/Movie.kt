package com.jg.tmbdapp.features.utils.models

data class Movie(
    val list:List<MovieItem>,
    val page: Int,
    val totalPages: Int,
    val totalResults: Int
)
