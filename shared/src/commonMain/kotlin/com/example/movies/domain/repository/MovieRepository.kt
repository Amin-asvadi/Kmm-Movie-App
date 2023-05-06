package com.example.movies.domain.repository

import com.example.movies.domain.model.Movie

internal interface MovieRepository {
    suspend fun geMovies(page:Int):List<Movie>
    suspend fun getMovie(movieId:Int) :Movie
}