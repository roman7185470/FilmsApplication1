package ru.lishukroman.filmsapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.lishukroman.filmsapplication.databinding.AdapterMovieBinding

class FilmRecyclerViewAdapter: RecyclerView.Adapter<FilmRecyclerViewHolder>() {

    var movies = mutableMapOf<String,List<Movie>>()

    fun setMovieList(movies: Map<String,List<Movie>>) {
        this.movies = movies.toMutableMap()
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmRecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = AdapterMovieBinding.inflate(inflater, parent, false)

        return FilmRecyclerViewHolder(binding)

    }

    override fun onBindViewHolder(holder: FilmRecyclerViewHolder, position: Int) {
        val movie = movies.get("items")!![position]
        holder.binding.name.text = movie.title
        holder.binding.director.text = movie.directorName

    }

    override fun getItemCount(): Int {
        if(!movies.isEmpty())
            return movies!!.get("items")!!.size
        return 0
    }
}

class FilmRecyclerViewHolder(val binding: AdapterMovieBinding): RecyclerView.ViewHolder(binding.root)