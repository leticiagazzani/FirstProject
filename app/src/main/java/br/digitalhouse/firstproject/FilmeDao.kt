package br.digitalhouse.firstproject

import androidx.room.*

//Banco de Dados - DataBase


@Dao
interface FilmeDao {

    @Query("SELECT * FROM Filme")
    fun buscaTodos(): List<Filme>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun salva(filme: Filme)

    @Delete
    fun remove(filme: Filme)
    

    @Query("SELECT * FROM filme WHERE id = :id ")
    fun buscaPorId(id: Long?): Filme?

}