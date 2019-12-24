package com.alharoof.movieez

import com.google.gson.annotations.SerializedName

class GetMoviesResponse {

    var status: String? = null
    var data: Data? = null
    @SerializedName("status_message")
    var statusMessage: String? = null
    @SerializedName("@meta")
    var meta: Meta? = null
}

class Data {
    var movies: List<Movie>? = null
    var limit: Int? = null
    @SerializedName("movie_count")
    var movieCount: Int? = null
    @SerializedName("page_number")
    var pageNumber: Int? = null
}

class Meta {
    @SerializedName("server_time")
    var serverTime: Int? = null
    @SerializedName("server_timezone")
    var serverTimezone: String? = null
    @SerializedName("api_version")
    var apiVersion: Int? = null
    @SerializedName("execution_time")
    var executionTime: String? = null
}