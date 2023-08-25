package com.example.composemovie.presentation.movie_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composemovie.domain.use_case.get_movie_detail.GetMovieDetailUseCase
import com.example.composemovie.util.Constants.IMDB_ID
import com.example.composemovie.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf<MovieDetailState>(MovieDetailState())
    val state: State<MovieDetailState> = _state

    init {
        savedStateHandle.get<String>(IMDB_ID)?.let {
            getMovieDetail(it)
        }
    }
    //getting Imdb id for single result
    private fun getMovieDetail(imdbId: String) {
        getMovieDetailsUseCase.executeGetMovieDetails(imdbId = imdbId).onEach {
            println("Detail "+it.data)
            when (it) {
                is Resource.Success -> {
                    println("Detail" + it.data)
                    _state.value = MovieDetailState(movieDetail = it.data)
                }

                is Resource.Error -> {
                    _state.value = MovieDetailState(error = it.message ?: "Error!")
                    println("Detail Error" + it.message)
                }

                is Resource.Loading -> {
                    println("Loading...........")
                    _state.value = MovieDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
