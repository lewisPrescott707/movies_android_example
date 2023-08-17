package com.example.composemovie.data.di

import com.example.composemovie.data.remote.MovieAPI
import com.example.composemovie.data.repository.MovieRepositoryImpl
import com.example.composemovie.domain.repository.IMovieRepository
import com.example.composemovie.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieApp(): MovieAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api: MovieAPI): IMovieRepository {
        return MovieRepositoryImpl(api = api)
    }
}