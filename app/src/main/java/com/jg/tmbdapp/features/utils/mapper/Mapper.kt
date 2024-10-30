package com.jg.tmbdapp.features.utils.mapper

import com.jg.tmbdapp.features.popular.data.model.PopularDTO
import com.jg.tmbdapp.features.search.data.model.SearchDTO
import com.jg.tmbdapp.features.popular.domain.model.Popular
import com.jg.tmbdapp.features.search.domain.model.Search
import com.jg.tmbdapp.features.utils.models.MovieItem

fun PopularDTO.toMap(): Popular {
    val list = this.results.map {
        MovieItem(
            adult = it.adult,
            backdropPath =it.backdrop_path,
            id = it.id,
            originalLanguage = it.original_language,
            overview = it.overview,
            popularity = it.popularity,
            posterPath = it.poster_path,
            voteAverage = it.vote_average,
            voteCount = it.vote_count,
            title = it.title,
            genreList = it.genre_ids,
            originalTitle = it.original_title,
            releaseDate = it.release_date
        )
    }
    return Popular(
        listPopular =list,
        page = this.page,
        total_pages = this.total_pages,
        total_results = this.total_results)
}

fun SearchDTO.toMap(): Search {
    val list = this.results.map {
        MovieItem(
            adult = it.adult,
            backdropPath = it.backdrop_path,
            id = it.id,
            originalLanguage = it.original_language,
            overview = it.overview,
            popularity = it.popularity,
            posterPath = it.poster_path,
            voteAverage = it.vote_average,
            voteCount = it.vote_count,
            title = it.title,
            genreList = it.genre_ids,
            originalTitle = it.original_title,
            releaseDate = it.release_date
        )
    }

    return Search(listPopular = list,
        page = page,
        total_pages = total_pages,
        total_results = total_results)
}