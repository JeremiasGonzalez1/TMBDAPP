package com.jg.tmbdapp.features.utils.mapper

import com.jg.tmbdapp.features.popular.data.model.PopularDTO
import com.jg.tmbdapp.features.search.data.model.SearchDTO
import com.jg.tmbdapp.features.upcoming.data.model.UpComingDTO
import com.jg.tmbdapp.features.utils.models.Movie
import com.jg.tmbdapp.features.utils.models.MovieItem

fun PopularDTO.toMap(): Movie {
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
    return Movie(
        list =list,
        page = this.page,
        totalPages = this.total_pages,
        totalResults = this.total_results)
}

fun SearchDTO.toMap(): Movie {
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

    return Movie(list = list,
        page = page,
        totalPages = total_pages,
        totalResults = total_results)
}

fun UpComingDTO.toModel() : Movie {
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

    return Movie(
        list = list,
        page = page,
        totalPages = total_pages,
        totalResults = total_results
    )
}