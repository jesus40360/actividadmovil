package com.example.proyectomovil

import java.io.Serializable
import kotlin.random.Random

class Cotizacion : Serializable {
    var numCotizacion: Int = 0
    var descripcion: String = ""
    var porPagInicial: Float = 0.0f
    var precio: Float = 0.0f
    var plazos: Int = 0

    fun calcularPagoInicial(): Float {
        return precio * (porPagInicial / 100)
    }

    fun calcularTotalFin(): Float {
        return precio - calcularPagoInicial()
    }

    fun calcularPagoMensual(): Float {
        return calcularTotalFin() / plazos
    }

    fun generaFolio(): Int {
        return Random(System.currentTimeMillis()).nextInt(1000, 9999)
    }
}
