package com.example.movies.data.remote

import kotlinx.serialization.Serializable

@Serializable
internal data class MovieResult(
    val results :List<MovieRemote>
)
