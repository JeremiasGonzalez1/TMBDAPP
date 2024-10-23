package com.jg.tmbdapp.features.search.domain.model

import com.jg.tmbdapp.features.utils.models.MovieItem

data class Search(
    val listPopular:List<MovieItem>,
    val page: Int,
    val total_pages: Int,
    val total_results: Int
)