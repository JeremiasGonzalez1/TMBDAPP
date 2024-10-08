package com.jg.tmbdapp.domain.popular.model

import com.jg.tmbdapp.domain.utils.MovieItem

data class Popular(
    val listPopular:List<MovieItem>,
    val page: Int,
    val total_pages: Int,
    val total_results: Int
)