package ru.lishukroman.filmsapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository)  : ViewModel() {

    val movieList = MutableLiveData<Map<String,List<Movie>>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllMovies() {

        val response = repository.getAllMovies()
        response.enqueue(object : Callback<Map<String,List<Movie>>> {
            override fun onResponse(call: Call<Map<String,List<Movie>>>, response: Response<Map<String,List<Movie>>>)
            {
                movieList.postValue(response.body())
            }

            override fun onFailure(call: Call<Map<String,List<Movie>>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}