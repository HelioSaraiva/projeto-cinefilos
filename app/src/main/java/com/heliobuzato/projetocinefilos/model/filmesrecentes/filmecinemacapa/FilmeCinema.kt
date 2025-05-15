package com.heliobuzato.projetocinefilos.model.filmesrecentes.filmecinemacapa

import com.google.gson.annotations.SerializedName


data class FilmeCinema(
    val dates: Dates,
    val page: Int,
    @SerializedName("results")
    val filmeEmCartaz: List<FilmeResulCinema>,
    val total_pages: Int,
    val total_results: Int
)