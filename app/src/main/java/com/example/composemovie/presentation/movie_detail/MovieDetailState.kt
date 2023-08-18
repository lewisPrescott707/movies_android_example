package com.example.composemovie.presentation.movie_detail

import com.example.composemovie.domain.model.MovieDetail

data class MovieDetailState(
    val isLoading: Boolean = false,
    val movieDetail: MovieDetail? = null,
    val error: String = ""
)
