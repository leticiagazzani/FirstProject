package br.digitalhouse.firstproject


import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import br.digitalhouse.firstproject.databinding.ActivityFormularioImagemBinding

class FormularioImagemDialog(private val context: Context) {

    fun mostra(
        urlPadrao: String? = null,
        quandoImagemCarregada: (imagem: String) -> Unit
    ) {

        ActivityFormularioImagemBinding.inflate(LayoutInflater.from(context)).apply {
            urlPadrao?.let {
                formularioImagemImageview.tentaCarregarImagem(it)
                formularioImagemUrl
            }

            formularioBotaoCarregar.setOnClickListener {
                val url = formularioImagemUrl.text.toString()
                formularioImagemImageview.tentaCarregarImagem(url)
            }
            AlertDialog.Builder(context)
                .setView(root)
                .setPositiveButton("Confirmar") { _, _ ->
                    val url = formularioImagemUrl.text.toString()
                    quandoImagemCarregada(url)
                }

                .setNegativeButton("Cancelar") { _, _ -> }
                .show()

        }


    }


}