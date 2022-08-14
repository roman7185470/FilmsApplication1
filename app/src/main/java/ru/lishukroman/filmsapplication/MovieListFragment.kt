package ru.lishukroman.filmsapplication

import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import ru.lishukroman.filmsapplication.databinding.FragmentMovieListBinding

class MovieListFragment : Fragment() {

    private val TAG: String = "MovieListFragment"
    private lateinit var fragmentMovieListBinding: FragmentMovieListBinding
    lateinit var viewModel: MainViewModel

    private val retrofitService = RetrofitService.getInstance()
    val adapter = FilmRecyclerViewAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMovieListBinding.bind(view)
        fragmentMovieListBinding = binding


        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)

        val linearLayoutManager:LinearLayoutManager = LinearLayoutManager(view.context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerviewFilms.layoutManager = linearLayoutManager
        binding.recyclerviewFilms.adapter = adapter




        viewModel.movieList.observe(viewLifecycleOwner, Observer {
            adapter.setMovieList(it)
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {

            Log.d(TAG,"FragmentErrorMessage")
        })
        viewModel.getAllMovies()

    }
}