package com.desafio.productexplorer.util

import java.text.SimpleDateFormat

object UtilFormat {
    fun getDateFormatted(date: String): String {
        try {
            // Crear un objeto SimpleDateFormat para el formato de entrada
            val formatoEntrada = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

            // Crear un objeto SimpleDateFormat para el formato de salida (día/mes/año)
            val formatoSalida = SimpleDateFormat("dd/MM/yyyy")

            // Parsear la fecha original a un objeto Date
            val fechaDate = formatoEntrada.parse(date)

            // Formatear la fecha a "día/mes/año"
            return formatoSalida.format(fechaDate)
        } catch (e: Exception) {
            e.printStackTrace()
            return "Error al formatear la fecha"
        }
    }
}