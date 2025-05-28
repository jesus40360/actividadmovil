package com.example.proyectomovil

import android.app.AlertDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectomovil.Cotizacion


class CotizacionActividad : AppCompatActivity() {


    private lateinit var txtCliente: TextView
    private lateinit var txtFolio: TextView
    private lateinit var txtDescripcion: EditText
    private lateinit var txtPorcentaje: EditText
    private lateinit var txtPrecio: EditText
    private lateinit var txtPlazos: EditText
    private lateinit var txtPagoInicial: TextView
    private lateinit var txtTotalFin: TextView
    private lateinit var txtPagoMensual: TextView

    private lateinit var btnCalcular: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnCerrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cotizacion_actividad)

        txtCliente = findViewById(R.id.txtCliente)
        txtFolio = findViewById(R.id.txtFolio)
        txtDescripcion = findViewById(R.id.txtDescripcion)
        txtPorcentaje = findViewById(R.id.txtPorcentaje)
        txtPrecio = findViewById(R.id.txtPrecio)
        txtPlazos = findViewById(R.id.txtPlazos)
        txtPagoInicial = findViewById(R.id.txtPagoInicial)
        txtTotalFin = findViewById(R.id.txtTotalFin)
        txtPagoMensual = findViewById(R.id.txtPagoMensual)

        btnCalcular = findViewById(R.id.btnCalcular)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnCerrar = findViewById(R.id.btnCerrar)


        val cliente = intent.getStringExtra("cliente") ?: "Cliente desconocido"
        txtCliente.text = "Cliente: $cliente"


        val cotizacion = Cotizacion()
        cotizacion.numCotizacion = cotizacion.generaFolio()
        txtFolio.text = "Folio: ${cotizacion.numCotizacion}"


        btnCalcular.setOnClickListener {
            cotizacion.descripcion = txtDescripcion.text.toString()
            cotizacion.precio = txtPrecio.text.toString().toFloatOrNull() ?: 0f
            cotizacion.porPagInicial = txtPorcentaje.text.toString().toFloatOrNull() ?: 0f
            cotizacion.plazos = txtPlazos.text.toString().toIntOrNull() ?: 1

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
            txtPlazos.text.clear()
            txtPagoInicial.text = ""
            txtTotalFin.text = ""
            txtPagoMensual.text = ""
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
