package com.alharoof.movieez

import io.reactivex.Single
import retrofit2.http.GET

interface YtsApi {

    @GET("list_movies.json")
    fun getMovies(): Single<GetMoviesResponse>
}