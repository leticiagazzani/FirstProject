package br.digitalhouse.firstproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import br.digitalhouse.firstproject.databinding.ActivityListaFilmesActivityBinding

class ListaFilmesActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityListaFilmesActivityBinding.inflate(layoutInflater)
    }

    private val adapter = ListaDeFilmesAdapter(context = this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraRecyclerView()
        configuraFab()


    }

    override fun onResume() {
        super.onResume()
        val db = AppDatabase.instancia(this)
        val filmeDao = db.filmeDao()
        adapter.atualiza(filmeDao.buscaTodos())

    }

    private fun configuraRecyclerView() {
        val recyclerview = binding.recyclerviewFilme
        recyclerview.adapter = adapter
        adapter.quandoClicaNoItem = {
            val intent = Intent(
                this,
                DetalhesFilme::class.java
            ).apply {
                putExtra(CHAVE_FILME_ID, it.id)
            }
            startActivity(intent)
        }
    }

    private fun configuraFab() {
        val fab = binding.floatingActionButton
        fab.setOnClickListener {
            vaiParaFormularioFilme()
        }
    }

    private fun vaiParaFormularioFilme() {
        val intent = Intent(this, FormularioFilme::class.java)
        startActivity(intent)
    }

//recyclerview.layoutManager = LinearLayoutManager(this)

}
