package com.example.proyectomovil

import android.app.AlertDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class CotizacionActividad : AppCompatActivity() {

    private lateinit var txtCliente: TextView
    private lateinit var txtFolio: TextView
    private lateinit var txtDescripcion: EditText
    private lateinit var txtPorcentaje: EditText
    private lateinit var txtPrecio: EditText
    private lateinit var txtPagoInicial: TextView
    private lateinit var txtTotalFin: TextView
    private lateinit var txtPagoMensual: TextView

    private lateinit var btnCalcular: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnCerrar: Button

    private lateinit var rdb12: RadioButton
    private lateinit var rdb24: RadioButton
    private lateinit var rdb36: RadioButton
    private lateinit var rdb48: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cotizacion_actividad)

        txtCliente = findViewById(R.id.txtCliente)
        txtFolio = findViewById(R.id.txtFolio)
        txtDescripcion = findViewById(R.id.txtDescripcion)
        txtPorcentaje = findViewById(R.id.txtPorcentaje)
        txtPrecio = findViewById(R.id.txtPrecio)
        txtPagoInicial = findViewById(R.id.txtPagoInicial)
        txtTotalFin = findViewById(R.id.txtTotalFin)
        txtPagoMensual = findViewById(R.id.txtPagoMensual)

        btnCalcular = findViewById(R.id.btnCalcular)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnCerrar = findViewById(R.id.btnCerrar)

        rdb12 = findViewById(R.id.rdb12)
        rdb24 = findViewById(R.id.rdb24)
        rdb36 = findViewById(R.id.rdb36)
        rdb48 = findViewById(R.id.rdb48)

        val cliente = intent.getStringExtra("cliente") ?: "Cliente desconocido"
        txtCliente.text = "Cliente: $cliente"

        val cotizacion = Cotizacion()
        cotizacion.numCotizacion = cotizacion.generaFolio()
        txtFolio.text = "Folio: ${cotizacion.numCotizacion}"

        btnCalcular.setOnClickListener {
            cotizacion.descripcion = txtDescripcion.text.toString()
            cotizacion.precio = txtPrecio.text.toString().toFloatOrNull() ?: 0f
            cotizacion.porPagInicial = txtPorcentaje.text.toString().toFloatOrNull() ?: 0f

            cotizacion.plazos = when {
                rdb12.isChecked -> 12
                rdb24.isChecked -> 24
                rdb36.isChecked -> 36
                rdb48.isChecked -> 48
                else -> 12
            }

            val pagoInicial = cotizacion.calcularPagoInicial()
            val totalFin = cotizacion.calcularTotalFin()
            val pagoMensual = cotizacion.calcularPagoMensual()

            txtPagoInicial.text = "Pago Inicial: $${"%.2f".format(pagoInicial)}"
            txtTotalFin.text = "Total a Financiar: $${"%.2f".format(totalFin)}"
            txtPagoMensual.text = "Pago Mensual: $${"%.2f".format(pagoMensual)}"
        }

        btnLimpiar.setOnClickListener {
            txtDescripcion.text.clear()
            txtPorcentaje.text.clear()
            txtPrecio.text.clear()

            txtPagoInicial.text = getString(R.string.pinicial)
            txtTotalFin.text = getString(R.string.tfin)
            txtPagoMensual.text = getString(R.string.pmensual)

            rdb12.isChecked = true
        }

        btnCerrar.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Cerrar Cotización")
                .setMessage("¿Deseas salir de la cotización?")
                .setPositiveButton("Aceptar") { _, _ -> finish() }
                .setNegativeButton("Cancelar", null)
                .setNeutralButton("Quizá") { _, _ ->
                    Toast.makeText(this, "Puedes terminar después", Toast.LENGTH_SHORT).show()
                }
                .show()
        }
    }
}
