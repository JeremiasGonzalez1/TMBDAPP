package com.jg.tmbdapp.domain.popular.model

data class Popular(
    val listPopular:List<PopularItem>,
    val page: Int,
    val total_pages: Int,
    val total_results: Int
)

data class PopularItem(
    val adult: Boolean,
    val backdrop_path: String,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int
)