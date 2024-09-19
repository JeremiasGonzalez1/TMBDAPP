package com.jg.tmbdapp.data.utils.mapper

import com.jg.tmbdapp.data.popular.model.PopularDTO
import com.jg.tmbdapp.data.search.model.SearchDTO
import com.jg.tmbdapp.domain.popular.model.Popular
import com.jg.tmbdapp.domain.search.model.Search
import com.jg.tmbdapp.domain.utils.MovieItem

fun PopularDTO.toMap(): Popular {
    val list = this.results.map {
        MovieItem(
            adult = it.adult,
            backdrop_path =it.backdrop_path,
            id = it.id,
            original_language = it.original_language,
            overview = it.overview,
            popularity = it.popularity,
            poster_path = it.poster_path,
            vote_average = it.vote_average,
            vote_count = it.vote_count,
            title = it.title,
            original_title = it.original_title,
        )
    }
    return Popular(
        listPopular =list,
        page = this.page,
        total_pages = this.total_pages,
        total_results = this.total_results)
}

fun SearchDTO.toMap():Search{
    val list = this.results.map {
        MovieItem(
            adult = it.adult,
            backdrop_path = it.backdrop_path,
            id = it.id,
            original_language = it.original_language,
            overview = it.overview,
            popularity = it.popularity,
            poster_path = it.poster_path,
            vote_average = it.vote_average,
            vote_count = it.vote_count,
            title = it.title,
            original_title = it.original_title
        )
    }

    return Search(listPopular = list,
        page = page,
        total_pages = total_pages,
        total_results = total_results)
}