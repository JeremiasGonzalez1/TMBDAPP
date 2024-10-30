package com.jg.tmbdapp.features.utils.models

data class MovieItem(
    val adult: Boolean,
    val backdropPath: String,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val title: String,
    val genreList:List<Int>,
    val releaseDate: String,
    val voteAverage: Double,
    val voteCount: Int
)