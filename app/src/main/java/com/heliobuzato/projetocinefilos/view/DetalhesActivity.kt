package com.heliobuzato.projetocinefilos.view

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.heliobuzato.projetocinefilos.R
import com.heliobuzato.projetocinefilos.control.adapter_api.RetrofitHelper
import com.heliobuzato.projetocinefilos.databinding.ActivityDetalhesBinding
import com.heliobuzato.projetocinefilos.model.filmesrecentes.filmecinemacapa.FilmeResulCinema
import com.heliobuzato.projetocinefilos.model.filmesrecentes.filmespopulares.FilmesResultPopulares
import com.squareup.picasso.Picasso

class DetalhesActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetalhesBinding.inflate(layoutInflater)
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var estaFavoritado = false

        binding.fabFavorito.setOnClickListener {

            estaFavoritado =!estaFavoritado

            val icon = if (estaFavoritado) R.drawable.ic_coracao_cheio else R.drawable.ic_coracao_vazio
            binding.fabFavorito.setImageResource(icon)

        }



        binding.btnDescricaoVoltar.setOnClickListener {
            finish()
        }

        budlePopular()
        bundleCinema()



    }

    private fun bundleCinema() {
        val bundleCinema = intent.extras

        Log.i("info_api", "Bundle: $bundleCinema")
        if (bundleCinema != null){

            val filme = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundleCinema.getParcelable("cinema", FilmeResulCinema::class.java )
            } else {
                bundleCinema.getParcelable("cinema") as? FilmeResulCinema
            }
            if(filme != null){
                Log.i("info_api","Filme nao nulo")
                val nomeFilmePoster = filme.poster_path
                val tamanhoFilme = "w780"
                val urlBase = RetrofitHelper.BASE_URL_IMAGEM

                val urlFilme = urlBase + tamanhoFilme + nomeFilmePoster

                Picasso.get()
                    .load(urlFilme)
                    .placeholder(R.drawable.capa) // opcional: imagem enquanto carrega
                    .error(R.drawable.capa)
                    .into(binding.imgPorters)

                binding.textFilmeTitulos.text = filme.title
                binding.textDescricao.text = filme.overview
                binding.textNotaDescriO.text = String.format("Nota: %.1f", filme.vote_average)


            }else{ Log.i("info_api","Filme é nulo")}

        }

    }

    private fun budlePopular() {
        val bundle = intent.extras

        Log.i("info_api", "Bundle: $bundle")
        if (bundle != null){

            val filme = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable("popular", FilmesResultPopulares::class.java )
            } else {
                bundle.getParcelable("popular") as? FilmesResultPopulares
            }
            if(filme != null){
                Log.i("info_api","Filme nao nulo")
                val nomeFilmePoster = filme.poster_path
                val tamanhoFilme = "w780"
                val urlBase = RetrofitHelper.BASE_URL_IMAGEM

                val urlFilme = urlBase + tamanhoFilme + nomeFilmePoster

                Picasso.get()
                    .load(urlFilme)
                    .placeholder(R.drawable.capa) // opcional: imagem enquanto carrega
                    .error(R.drawable.capa)
                    .into(binding.imgPorters)

                binding.textFilmeTitulos.text = filme.title
                binding.textDescricao.text = filme.overview
                binding.textNotaDescriO.text = String.format("Nota: %.1f", filme.vote_average)


            }else{ Log.i("info_api","Filme é nulo")}

        }

    }
}