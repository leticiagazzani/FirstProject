package br.digitalhouse.firstproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import br.digitalhouse.firstproject.databinding.ActivityDetalhesFilmeBinding

class DetalhesFilme : AppCompatActivity() {

    private var filmeId: Long = 0L
    private var filme: Filme? = null
    private val binding by lazy {
        ActivityDetalhesFilmeBinding.inflate(layoutInflater)
    }

    private val filmeDao by lazy {

        AppDatabase.instancia(this).filmeDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tentaCarregarFilme()
    }

    override fun onResume() {
        super.onResume()
        buscaFilme()
    }

    private fun buscaFilme() {
        filme = filmeDao.buscaPorId(filmeId)
        filme?.let {
            preencheCampos(it)
        } ?: finish()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalhes_filme, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_detalhe_filme_remover -> {
                filme?.let {
                    filmeDao.remove(it)
                }

                finish()
            }
            R.id.menu_detalhe_filme_editar -> {
                Intent(this, FormularioFilme::class.java).apply {
                    putExtra(CHAVE_FILME_ID, filmeId)
                    startActivity(this)
                }
            }


        }

        return super.onOptionsItemSelected(item)
    }


    private fun tentaCarregarFilme() {
        filmeId = intent.getLongExtra(CHAVE_FILME_ID, 0L)

    }


private fun preencheCampos(filmeCarregando: Filme) {
    with(binding) {

        detalhesFilmeImagem.tentaCarregarImagem(filmeCarregando.imagem)
        detalheFilmeNome.text = filmeCarregando.nome
        detalheFilmeGenero.text = filmeCarregando.genero
        detalheFilmeAno.text = filmeCarregando.ano.toString()


    }
}
}