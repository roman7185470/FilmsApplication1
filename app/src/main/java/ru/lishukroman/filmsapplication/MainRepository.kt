package ru.lishukroman.filmsapplication

import retrofit2.Response

class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllMovies() = retrofitService.getAllMovies()
}