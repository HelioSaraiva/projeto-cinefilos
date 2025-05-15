package com.heliobuzato.projetocinefilos.model.filmesrecentes.filmespopulares

import com.google.gson.annotations.SerializedName

data class FilmePopular(
    val page: Int,
    @SerializedName("results")
    val filmes: List<FilmesResultPopulares>,
    val total_pages: Int,
    val total_results: Int
)