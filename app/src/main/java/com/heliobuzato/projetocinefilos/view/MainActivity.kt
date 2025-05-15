package com.heliobuzato.projetocinefilos.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.heliobuzato.projetocinefilos.R
import com.heliobuzato.projetocinefilos.control.adapter_api.FilmesCinemaAdapter
import com.heliobuzato.projetocinefilos.control.adapter_api.FilmesPopularesAdapter
import com.heliobuzato.projetocinefilos.control.adapter_api.RetrofitHelper
import com.heliobuzato.projetocinefilos.databinding.ActivityMainBinding
import com.heliobuzato.projetocinefilos.model.filmesrecentes.FilmeRecente
import com.heliobuzato.projetocinefilos.model.filmesrecentes.filmecinemacapa.FilmeCinema
import com.heliobuzato.projetocinefilos.model.filmesrecentes.filmespopulares.FilmePopular
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val filmeAPI by lazy {
        RetrofitHelper.filmeAPI
    }



    private lateinit var filmePopularesAdapter: FilmesPopularesAdapter
    private lateinit var filmeCinemaAdapter: FilmesCinemaAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }





        inicializarPopularesViews()
        inicializarCinemaViews()


        val btnSaibaMais = binding.btnSaibaMais

        btnSaibaMais.setOnClickListener {

        }


    }


    private fun inicializarCinemaViews() {
        filmeCinemaAdapter = FilmesCinemaAdapter{filme ->

            val intent = Intent(this, DetalhesActivity::class.java)

            intent.putExtra("cinema", filme)

            startActivity(intent)

        }
        binding.rvCinema.adapter = filmeCinemaAdapter

        binding.rvCinema.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
    }

    private fun inicializarPopularesViews() {

        filmePopularesAdapter = FilmesPopularesAdapter{filme ->

            val intent = Intent(this,DetalhesActivity::class.java)
            intent.putExtra("popular",filme)
            startActivity(intent)

        }
        binding.rvPopulares.adapter = filmePopularesAdapter

        binding.rvPopulares.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )


    }


    override fun onStart() {
        super.onStart()
        //recuperarFilmeRecente()
        recuperarFilmePopulares()
        recuperarFilmesCartazCapa()
        recuperarFilmesCinema()

    }


    private fun recuperarFilmesCinema() {

        lifecycleScope.launch(Dispatchers.IO) {

            var resposta: Response<FilmeCinema>? = null

            try {
                resposta = filmeAPI.recuperarFilmesCartaz()

            } catch (e: Exception) {
                exibirMensagem("Erro ao recuperar dados")


            }

            if (resposta != null) {

                if (resposta.isSuccessful) {

                    val filmeCinema = resposta.body()
                    val listaFilmes = filmeCinema?.filmeEmCartaz
                    if (listaFilmes != null && listaFilmes.isNotEmpty())



                        withContext(Dispatchers.Main) {


                            filmeCinemaAdapter.adicionarListaCinema(listaFilmes)

                        }


                } else {

                    exibirMensagem("[${resposta.code()}] - Não foi possivel recuperar o filme recente")

                }


            } else {

                exibirMensagem("Não foi posiivel recuperar os dados")


            }


        }

    }

    private fun recuperarFilmePopulares() {

        lifecycleScope.launch(Dispatchers.IO) {

            var resposta: Response<FilmePopular>? = null

            try {
                resposta = filmeAPI.recuperarFilmePopulares()

            } catch (e: Exception) {
                exibirMensagem("Erro ao recuperar dados")


            }

            if (resposta != null) {

                if (resposta.isSuccessful) {

                    val filmePopular = resposta.body()
                    val listaFilmes = filmePopular?.filmes
                    if (listaFilmes != null && listaFilmes.isNotEmpty())


                        withContext(Dispatchers.Main) {

                            filmePopularesAdapter.adicionarLista(listaFilmes)

                        }


                } else {

                    exibirMensagem("[${resposta.code()}] - Não foi possivel recuperar o filme recente")

                }


            } else {

                exibirMensagem("Não foi posiivel recuperar os dados")


            }


        }

    }

    private fun recuperarFilmeRecente() {

        lifecycleScope.launch(Dispatchers.IO) {

            var resposta: Response<FilmeRecente>? = null

            try {
                resposta = filmeAPI.recuperarFilmeRecente()

            } catch (e: Exception) {
                exibirMensagem("Erro ao recuperar dados")


            }

            if (resposta != null) {

                if (resposta.isSuccessful) {

                    val filmeRecente = resposta.body()
                    val nomeImagem = filmeRecente?.poster_path
                    val titulo = filmeRecente?.title
                    val url = RetrofitHelper.BASE_URL_IMAGEM + "w780" + nomeImagem

                    withContext(Dispatchers.Main) {
//                     val texto = "titulo: $titulo url: $url"
//                    binding.textPopular.text = texto
                        Picasso.get()
                            .load(url)
                            .placeholder(R.drawable.capa) // opcional: imagem enquanto carrega
                            .error(R.drawable.capa)       // opcional: imagem se der erro
                            .into(binding.imgCapa)
                    }


                } else {

                    exibirMensagem("[${resposta.code()}] - Não foi possivel recuperar o filme recente")

                }


            } else {

                exibirMensagem("Não foi posiivel recuperar os dados")


            }


        }

    }

    private fun recuperarFilmesCartazCapa() {
        lifecycleScope.launch {

            var resposta: retrofit2.Response<FilmeCinema>? = null
            try {

                resposta = filmeAPI.recuperarFilmesCartaz()

            } catch (e: Exception) {
                exibirMensagem("Erro ao fazer a requisição")

            }
            if (resposta != null) {
                if (resposta.isSuccessful) {

                    val filmeRespostaCaretaz = resposta.body()
                    val listaFilmesCartaz = filmeRespostaCaretaz?.filmeEmCartaz
                    if (listaFilmesCartaz != null && listaFilmesCartaz.isNotEmpty()) {

                        //Log.d("info_filmes_api", "Lista Filmes Cartaz: ")
                        val nomeImagem = listaFilmesCartaz?.get(0)?.backdrop_path
                        val url = RetrofitHelper.BASE_URL_IMAGEM + "w1280" + nomeImagem
                        Log.d("info_filmes_api", "Titulo: $nomeImagem ")
                       //rimeiroFilme = listaFilmesCartaz[0] // guardamos o primeiro filme


                        withContext(Dispatchers.Main) {
                            Picasso.get()
                                .load(url)
                                .error(R.drawable.capa)
                                .into(binding.imgCapa)
                        }

                    }
                } else {
                    exibirMensagem("[${resposta.code()}] - Não foi possivel recuperar o filme recente ")

                }

            } else {
                exibirMensagem("Não foi possivel fazer a requisição")
            }

        }
    }

    private fun exibirMensagem(mensagem: String) {
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show()
    }


}