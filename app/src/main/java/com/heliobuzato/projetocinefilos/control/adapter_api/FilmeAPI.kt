package com.heliobuzato.projetocinefilos.control.adapter_api

import com.heliobuzato.projetocinefilos.model.filmesrecentes.FilmeRecente
import com.heliobuzato.projetocinefilos.model.filmesrecentes.filmecinemacapa.FilmeCinema
import com.heliobuzato.projetocinefilos.model.filmesrecentes.filmespopulares.FilmePopular
import retrofit2.Response
import retrofit2.http.GET

interface FilmeAPI {

    @GET("movie/latest?api_key=${RetrofitHelper.API_KEY}${RetrofitHelper.IDIOMA}")
    suspend fun recuperarFilmeRecente() : Response<FilmeRecente>

    @GET("movie/popular?api_key=${RetrofitHelper.API_KEY}${RetrofitHelper.IDIOMA}")
    suspend fun recuperarFilmePopulares() : Response<FilmePopular>


    @GET("movie/now_playing?api_key=${RetrofitHelper.API_KEY}${RetrofitHelper.IDIOMA}")
    suspend fun recuperarFilmesCartaz() : Response<FilmeCinema>
}