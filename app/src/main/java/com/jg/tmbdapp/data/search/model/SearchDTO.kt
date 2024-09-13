package com.jg.tmbdapp.data.search.model

import com.jg.tmbdapp.data.utils.model.ItemMovieDto
import kotlinx.serialization.Serializable

@Serializable
data class SearchDTO(
    val page: Int,
    val results: List<ItemMovieDto>,
    val total_pages: Int,
    val total_results: Int
)
