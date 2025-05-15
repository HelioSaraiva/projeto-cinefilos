package com.heliobuzato.projetocinefilos.control.adapter_api

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.heliobuzato.projetocinefilos.R
import com.heliobuzato.projetocinefilos.databinding.ItemFilmeBinding
import com.heliobuzato.projetocinefilos.model.filmesrecentes.filmespopulares.FilmesResultPopulares
import com.squareup.picasso.Picasso

class FilmesPopularesAdapter(
    val onClik: (FilmesResultPopulares) -> Unit
): Adapter<FilmesPopularesAdapter.FilmesViewHolder>() {

         private var listaFilmes: List<FilmesResultPopulares> = emptyList()

    fun adicionarLista(lista: List<FilmesResultPopulares>){

        this.listaFilmes = lista
        notifyDataSetChanged()

    }



    inner class FilmesViewHolder(val binding: ItemFilmeBinding): ViewHolder(binding.root){

        fun bind(filme: FilmesResultPopulares){

            val nomeFilmePoster = filme.poster_path
            val tamanhoFilme = "w780"
            val urlBase = RetrofitHelper.BASE_URL_IMAGEM

            val urlFilme = urlBase + tamanhoFilme + nomeFilmePoster

            Picasso.get()
                .load(urlFilme)
                .placeholder(R.drawable.capa) // opcional: imagem enquanto carrega
                .error(R.drawable.capa)
                .into(binding.imgItemFilme)

            binding.textTitulo.text = filme.title
            binding.textNota.text = String.format("Nota: %.1f", filme.vote_average)
            binding.clItem.setOnClickListener {

                onClik(filme)

            }



        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmesViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemFilmeBinding.inflate(

            layoutInflater,
            parent,
            false

        )
        return FilmesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmesViewHolder, position: Int) {

        val filmes = listaFilmes[position]
        holder.bind(filmes)

    }

    override fun getItemCount(): Int {
        return listaFilmes.size
    }

}