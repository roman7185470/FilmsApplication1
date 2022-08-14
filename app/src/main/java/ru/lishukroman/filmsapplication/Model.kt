package ru.lishukroman.filmsapplication

data class Movie(val title: String,
                 val directorName: String,
                 val releaseYear: String,
                 val actors: List<Map<String, String>>
)