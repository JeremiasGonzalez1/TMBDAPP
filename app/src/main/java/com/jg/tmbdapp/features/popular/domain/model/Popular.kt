package com.jg.tmbdapp.features.popular.domain.model

import com.jg.tmbdapp.features.utils.models.MovieItem

data class Popular(
    val listPopular:List<MovieItem>,
    val page: Int,
    val total_pages: Int,
    val total_results: Int
)