package com.heliobuzato.projetocinefilos.control.adapter_api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitHelper {

    companion object{
        const val API_KEY = "735ee17e1ced0171d4f8f486948c77c4"
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val BASE_URL_IMAGEM = "https://image.tmdb.org/t/p/"
        const val IDIOMA = "&language=pt-BR"
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val filmeAPI = retrofit.create(FilmeAPI::class.java)

    }

}