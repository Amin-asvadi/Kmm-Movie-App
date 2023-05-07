package com.example.movies.data.remote



@kotlinx.serialization.Serializable
internal data class MovieResult(
    val results :List<MovieRemote>
)
