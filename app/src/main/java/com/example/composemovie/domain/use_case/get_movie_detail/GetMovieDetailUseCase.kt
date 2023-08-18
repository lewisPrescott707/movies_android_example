package com.example.composemovie.domain.use_case.get_movie_detail

import com.example.composemovie.data.remote.dto.toMovieDetail
import com.example.composemovie.domain.model.MovieDetail
import com.example.composemovie.domain.repository.IMovieRepository
import com.example.composemovie.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import java.lang.Exception
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(private val repository: IMovieRepository) {

    fun executeGetMovieDetails(imdbId: String): Flow<Resource<MovieDetail>> = flow {
        try {
            emit(Resource.Loading())
            val movieDetail = repository.getMovieDetail(imdbId = imdbId).toMovieDetail()
            emit(Resource.Success(movieDetail))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "Exception Error!"))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error!"))
        } catch (e: IOError) {
            emit(Resource.Error(message = "Could not reach internet"))
        }
    }
}