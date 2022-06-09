package br.digitalhouse.firstproject

import androidx.room.TypeConverter
import java.math.BigDecimal

class Converters {


    @TypeConverter
    fun deDouble(ano: Double?): BigDecimal {
        return ano?.let { BigDecimal(ano.toString()) } ?: BigDecimal.ZERO

    }

    @TypeConverter
    fun BigDecimalParaDouble(ano: BigDecimal?): Double? {
        return ano?.let { ano.toDouble() }

    }


}
