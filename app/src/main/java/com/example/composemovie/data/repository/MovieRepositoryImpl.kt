package com.example.composemovie.data.repository

import com.example.composemovie.data.remote.MovieAPI
import com.example.composemovie.data.remote.dto.MovieDetailDto
import com.example.composemovie.data.remote.dto.MoviesDto
import com.example.composemovie.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val api: MovieAPI) : MovieRepository {
    override suspend fun getMovies(search: String): MoviesDto {
        return api.getMovies(search)
    }

    override suspend fun getMovieDetail(imdbId: String): MovieDetailDto {
        return api.getMovieDetail(imdbId)
    }

}