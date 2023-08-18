package com.example.composemovie.domain.use_case.get_movie_detail

import com.example.composemovie.data.remote.dto.toMovieDetail
import com.example.composemovie.domain.model.MovieDetail
import com.example.composemovie.domain.repository.IMovieRepository
import com.example.composemovie.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetMovieDetails @Inject constructor(private val repository: IMovieRepository) {

    fun executeGetDetailsMovies(imdbId: String): Flow<Resource<MovieDetail>> = flow {
        try {
            emit(Resource.Loading())
            val movieDetail = repository.getMovieDetail(imdbId)
            emit(Resource.Success(data = movieDetail.toMovieDetail()))
        } catch (e: IOError) {
            emit(Resource.Error(message = "No internet connection"))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error"))
        }
    }
}