package com.example.composemovie.domain.repository

import com.example.composemovie.data.remote.dto.MovieDetailDto
import com.example.composemovie.data.remote.dto.MoviesDto

interface IMovieRepository {
    suspend fun getMovies(search: String): MoviesDto
    suspend fun getMovieDetail(imdbId: String): MovieDetailDto
}