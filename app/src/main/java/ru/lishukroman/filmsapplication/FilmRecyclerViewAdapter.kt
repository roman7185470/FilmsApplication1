package ru.lishukroman.filmsapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ru.lishukroman.filmsapplication.databinding.AdapterMovieBinding

class FilmRecyclerViewAdapter(private val callBackCommunicator: CallBackCommunicator): RecyclerView.Adapter<FilmRecyclerViewHolder>() {

    private val TAG: String = "FilmRecyclerViewAdapter"

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

        holder.binding.root.setOnClickListener {
            Toast.makeText(holder.binding.root.context, "Кликаем на пункт ${holder.binding.name.text}",Toast.LENGTH_SHORT).show()

            Log.d(TAG,holder.binding.name.text.toString())

            this.callBackCommunicator.passData(movie)
        }

    }

    override fun getItemCount(): Int {
        if(!movies.isEmpty())
            return movies!!.get("items")!!.size
        return 0
    }
}

class FilmRecyclerViewHolder(val binding: AdapterMovieBinding): RecyclerView.ViewHolder(binding.root)