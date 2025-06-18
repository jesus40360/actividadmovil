package com.example.proyectomovil

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class NominaActividad : AppCompatActivity() {

    private lateinit var txtRecibo: EditText
    private lateinit var txtHoras: EditText
    private lateinit var txtExtras: EditText
    private lateinit var txtNombre: TextView

    private lateinit var rdbAuxiliar: RadioButton
    private lateinit var rdbAlbanil: RadioButton
    private lateinit var rdbIng: RadioButton

    private lateinit var txtSubtotal: TextView
    private lateinit var txtImpuesto: TextView
    private lateinit var txtTotal: TextView

    private lateinit var btnCalcular: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnRegresar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nomina_actividad)

        txtRecibo = findViewById(R.id.txtRecibo)
        txtNombre = findViewById(R.id.txtNombre)
        txtHoras = findViewById(R.id.txtHoras)
        txtExtras = findViewById(R.id.txtExtras)

        rdbAuxiliar = findViewById(R.id.rdbAuxiliar)
        rdbAlbanil = findViewById(R.id.rdbAlbanil)
        rdbIng = findViewById(R.id.rdbIng)

        txtSubtotal = findViewById(R.id.txtSubtotal)
        txtImpuesto = findViewById(R.id.txtImpuesto)
        txtTotal = findViewById(R.id.txtTotal)

        btnCalcular = findViewById(R.id.btnCalcular)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnRegresar = findViewById(R.id.btnRegresar)

        // Nombre recibido del login
        val nombre = intent.getStringExtra("nombre") ?: "Trabajador"
        txtNombre.text = nombre

        // Generar número de recibo aleatorio entre 1000 y 9999
        val folio = Random(System.currentTimeMillis()).nextInt(1000, 9999)
        txtRecibo.setText(folio.toString())

        btnCalcular.setOnClickListener {
            val horas = txtHoras.text.toString().toFloatOrNull() ?: 0f
            val extras = txtExtras.text.toString().toFloatOrNull() ?: 0f

            val pagoBase = 200f
            val porcentaje = when {
                rdbAuxiliar.isChecked -> 0.20f // +20%
                rdbAlbanil.isChecked -> 0.50f // +50%
                rdbIng.isChecked -> 1.00f     // +100%
                else -> 0f
            }

            val pagoHora = pagoBase + (pagoBase * porcentaje)

            val subtotal = (horas * pagoHora) + (extras * pagoHora * 2)
            val impuesto = subtotal * 0.16
            val total = subtotal - impuesto

            txtSubtotal.text = "Subtotal: $${"%.2f".format(subtotal)}"
            txtImpuesto.text = "Impuesto: $${"%.2f".format(impuesto)}"
            txtTotal.text = "Total a pagar: $${"%.2f".format(total)}"
        }

        btnLimpiar.setOnClickListener {
            // Regenerar nuevo número de recibo
            val nuevoFolio = Random(System.currentTimeMillis()).nextInt(1000, 9999)
            txtRecibo.setText(nuevoFolio.toString())

            txtHoras.text.clear()
            txtExtras.text.clear()
            rdbAuxiliar.isChecked = true
            txtSubtotal.text = "Subtotal: $0.00"
            txtImpuesto.text = "Impuesto: $0.00"
            txtTotal.text = "Total a pagar: $0.00"
        }

        btnRegresar.setOnClickListener {
            finish()
        }
    }
}
