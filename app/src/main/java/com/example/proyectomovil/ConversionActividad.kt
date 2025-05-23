package com.example.proyectomovil

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class ConversionActividad : AppCompatActivity() {

    private lateinit var txtGrados: EditText
    private lateinit var radioCelsius: RadioButton
    private lateinit var radioFahrenheit: RadioButton
    private lateinit var btnConvertir: Button
    private lateinit var lblResultado: TextView
    private lateinit var btnLimpiar: Button
    private lateinit var btnSalir: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversion_actividad)

        // Inicializar componentes
        txtGrados = findViewById(R.id.txtGrados)
        radioCelsius = findViewById(R.id.radioCelsius)
        radioFahrenheit = findViewById(R.id.radioFahrenheit)
        btnConvertir = findViewById(R.id.btnConvertir)
        lblResultado = findViewById(R.id.lblResultado)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnSalir = findViewById(R.id.btnSalir)

        // Configurar eventos
        btnConvertir.setOnClickListener {
            convertirTemperatura()
        }

        btnLimpiar.setOnClickListener {
            limpiarCampos()
        }

        btnSalir.setOnClickListener {
            mostrarDialogoSalir()
        }
    }

    private fun convertirTemperatura() {
        val entrada = txtGrados.text.toString().trim()

        if (entrada.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa un valor", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            val valor = entrada.toDouble()
            val resultado = if (radioCelsius.isChecked) {
                val fahrenheit = (valor * 9 / 5) + 32
                "Resultado: %.2f °F".format(fahrenheit)
            } else {
                val celsius = (valor - 32) * 5 / 9
                "Resultado: %.2f °C".format(celsius)
            }
            lblResultado.text = resultado
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Ingresa un número válido", Toast.LENGTH_SHORT).show()
        }
    }

    private fun limpiarCampos() {
        txtGrados.text.clear()
        lblResultado.text = ""
        radioCelsius.isChecked = true
    }

    private fun mostrarDialogoSalir() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Conversión de Temperatura")
            .setMessage("¿Deseas salir de la conversión?")
            .setPositiveButton("Aceptar") { _, _ ->
                finish()
            }
            .setNegativeButton("Cancelar") { _, _ ->
                Toast.makeText(applicationContext, "Operación cancelada", Toast.LENGTH_SHORT).show()
            }
            .setNeutralButton("Quizá") { _, _ ->
                Toast.makeText(applicationContext, "Quizá más tarde", Toast.LENGTH_SHORT).show()
            }
            .show()
    }
}
