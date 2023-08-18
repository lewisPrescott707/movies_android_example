package com.example.composemovie.presentation.movies

import com.example.composemovie.domain.model.Movie

data class MoviesState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String = "",
    val search: String = "Batman"
)