package com.example.proyectomovil

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MonedaActividad : AppCompatActivity() {

    private lateinit var etCantidad: EditText
    private lateinit var spinnerOrigen: Spinner
    private lateinit var spinnerDestino: Spinner
    private lateinit var tvResultado: TextView

    private val tasasCambio = mapOf(
        "MXN" to mapOf("USD" to 0.059, "CAD" to 0.081, "GBP" to 0.046, "MXN" to 1.0),
        "USD" to mapOf("MXN" to 17.0, "CAD" to 1.37, "GBP" to 0.78, "USD" to 1.0),
        "CAD" to mapOf("MXN" to 12.4, "USD" to 0.73, "GBP" to 0.57, "CAD" to 1.0),
        "GBP" to mapOf("MXN" to 21.7, "USD" to 1.28, "CAD" to 1.75, "GBP" to 1.0)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moneda_actividad)

        etCantidad = findViewById(R.id.etCantidad)
        spinnerOrigen = findViewById(R.id.spinnerOrigen)
        spinnerDestino = findViewById(R.id.spinnerDestino)
        tvResultado = findViewById(R.id.tvResultado)

        val monedas = listOf("MXN", "USD", "CAD", "GBP")
        val adapter = ArrayAdapter(this, R.layout.spinner_item, monedas)
        adapter.setDropDownViewResource(R.layout.spinner_item)

        spinnerOrigen.adapter = adapter
        spinnerDestino.adapter = adapter
    }

    fun convertirMoneda(view: View) {
        val cantidadTexto = etCantidad.text.toString()
        if (cantidadTexto.isEmpty()) {
            Toast.makeText(this, "Ingresa una cantidad", Toast.LENGTH_SHORT).show()
            return
        }

        val cantidad = cantidadTexto.toDoubleOrNull()
        if (cantidad == null) {
            Toast.makeText(this, "Cantidad inválida", Toast.LENGTH_SHORT).show()
            return
        }

        val origen = spinnerOrigen.selectedItem.toString()
        val destino = spinnerDestino.selectedItem.toString()

        val tasa = tasasCambio[origen]?.get(destino)
        if (tasa == null) {
            Toast.makeText(this, "Error en conversión", Toast.LENGTH_SHORT).show()
            return
        }

        val resultado = cantidad * tasa
        val resultadoRedondeado = String.format("%.2f", resultado)
        tvResultado.text = "Resultado: $resultadoRedondeado $destino"
    }

    fun limpiarCampos(view: View) {
        etCantidad.text.clear()
        spinnerOrigen.setSelection(0)
        spinnerDestino.setSelection(0)
        tvResultado.text = "Resultado:"
    }

    fun salirActividad(view: View) {
        val dialog = AlertDialog.Builder(this)
            .setTitle("Salir")
            .setMessage("¿Estás seguro de que quieres salir?")
            .setPositiveButton("Aceptar") { _, _ -> finish() }
            .setNegativeButton("Cancelar", null)
            .create()
        dialog.show()
    }
}
