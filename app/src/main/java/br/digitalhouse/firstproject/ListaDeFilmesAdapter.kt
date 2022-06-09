package br.digitalhouse.firstproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.digitalhouse.firstproject.databinding.ItemFilmeBinding


class ListaDeFilmesAdapter(
    private val context: Context,
    filmes: List<Filme> = emptyList(),
    var quandoClicaNoItem: (filme: Filme) -> Unit = {}
) : RecyclerView.Adapter<ListaDeFilmesAdapter.ViewHolder>() {

    private val filmes = filmes.toMutableList()

   inner class ViewHolder(private val binding: ItemFilmeBinding) :
        RecyclerView.ViewHolder(binding.root) {

       private lateinit var filme: Filme

       init {
           itemView.setOnClickListener {
               if (::filme.isInitialized) {
                   quandoClicaNoItem(filme)
               }
           }
       }


       fun vincula(filme: Filme) {
           this.filme = filme
            val nome = binding.nomefilme
            nome.text = filme.nome
            val genero = binding.generofilme
            genero.text = filme.genero
            val ano = binding.anofilme
            ano.text = filme.ano.toPlainString()

            val visibilidade = if (filme.imagem != null){
                View.VISIBLE
            } else{ View.GONE }

            binding.imagemfilme.visibility=visibilidade




            binding.imagemfilme.tentaCarregarImagem(filme.imagem)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemFilmeBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val filme = filmes[position]
        holder.vincula(filme)
    }

    override fun getItemCount(): Int = filmes.size
    fun atualiza(filmes: List<Filme>) {
        this.filmes.clear()
        this.filmes.addAll(filmes)
        notifyDataSetChanged()
    }


}