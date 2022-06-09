package br.digitalhouse.firstproject


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal


@Entity
@Parcelize
data class Filme(

    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val nome: String,
    val genero: String,
    val ano: BigDecimal,
    val imagem: String? = null
) : Parcelable
