package com.jg.tmbdapp.domain.search.model

import com.jg.tmbdapp.domain.utils.MovieItem

data class Search(
    val listPopular:List<MovieItem>,
    val page: Int,
    val total_pages: Int,
    val total_results: Int
)