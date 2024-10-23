package com.jg.tmbdapp.features.search.data.model

import com.jg.tmbdapp.features.utils.models.ItemMovieDto
import kotlinx.serialization.Serializable

@Serializable
data class SearchDTO(
    val page: Int,
    val results: List<ItemMovieDto>,
    val total_pages: Int,
    val total_results: Int
)
