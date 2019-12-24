package com.alharoof.movieez

import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Int,
    val title: String,
    val genres: List<String>,
    val rating: Float,
    val year: Int,
    @SerializedName("large_cover_image")
    val posterUrl: String
)