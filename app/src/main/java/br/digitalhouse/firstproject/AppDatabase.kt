package br.digitalhouse.firstproject

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [Filme::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun filmeDao(): FilmeDao

    companion object {
        fun instancia(context: Context): AppDatabase {
           return  Room.databaseBuilder(context,
                AppDatabase::class.java,
                "firstproject.db")
                .allowMainThreadQueries()
                .build()

        }
    }


}