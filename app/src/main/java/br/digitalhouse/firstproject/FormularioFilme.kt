package br.digitalhouse.firstproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.digitalhouse.firstproject.databinding.ActivityFormularioFilmeBinding
import java.math.BigDecimal

class FormularioFilme : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioFilmeBinding.inflate(layoutInflater)

    }
    private var url: String? = null
    private var filmeId = 0L
    private val filmeDao: FilmeDao by lazy {
        val db = AppDatabase.instancia(this)
        db.filmeDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Cadastrar filme"
        configuraBotaoSalvar()
        binding.imagemFilme.setOnClickListener {
            FormularioImagemDialog(this).mostra(url) { imagem ->
                url = imagem
                binding.imagemFilme.tentaCarregarImagem(url)

            }

        }

        tentaCarregarFilme()


    }

    private fun tentaCarregarFilme() {
        filmeId = intent.getLongExtra(CHAVE_FILME_ID, 0L)
    }

    override fun onResume() {
        super.onResume()
        tentaBuscarFilme()
    }

    private fun tentaBuscarFilme() {
        filmeDao.buscaPorId(filmeId)?.let {
            title = "Alterar Filme"
            preencheCampos(it)
        }
    }

    private fun preencheCampos(filme: Filme) {

        url = filme.imagem
        binding.imagemFilme.tentaCarregarImagem(filme.imagem)
        binding.filmeNome.setText(filme.nome)
        binding.filmeGenero.setText(filme.genero)
        binding.filmeAno.setText(filme.ano.toPlainString())
    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = binding.filmeBotaoSalvar

        botaoSalvar.setOnClickListener {
            val filmenovo = criaFilme()
//            if (filmeId > 0) {
//                filmeDao.altera(filmenovo)
//            } else {
//                filmeDao.salva(filmenovo)
//            }
            filmeDao.salva(filmenovo)
            finish()
        }
    }

    private fun criaFilme(): Filme {
        val campoNome = binding.filmeNome
        val nome = campoNome.text.toString()
        val campoGenero = binding.filmeGenero
        val genero = campoGenero.text.toString()
        val campoAno = binding.filmeAno
        val anoEmTexto = campoAno.text.toString()
        val ano = if (anoEmTexto.isBlank()) {
            BigDecimal.ZERO

        } else {
            BigDecimal(anoEmTexto)
        }

        return Filme(
            id = filmeId,
            nome = nome,
            genero = genero,
            ano = ano,
            imagem = url
        )
    }
}