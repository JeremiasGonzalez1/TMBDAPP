package com.jg.tmbdapp.data.mapper

import com.jg.tmbdapp.data.popular.models.PopularDTO
import com.jg.tmbdapp.domain.popular.model.Popular
import com.jg.tmbdapp.domain.popular.model.PopularItem

fun PopularDTO.toMap(): Popular {
    val list = this.results.map {
        PopularItem(
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