package com.example.proyectomovil

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ImcActividad : AppCompatActivity() {

    // Declaración de los objetos
    private lateinit var txtPeso: EditText
    private lateinit var txtEstatura: EditText
    private lateinit var btnCalcular: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnSalir: Button
    private lateinit var lblResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_actividad)

        iniciarComponentes()
        eventosBotones()
    }

    private fun iniciarComponentes() {
        txtPeso = findViewById(R.id.txtPeso)
        txtEstatura = findViewById(R.id.txtEstatura)
        btnCalcular = findViewById(R.id.btnCalcular)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnSalir = findViewById(R.id.btnSalir)
        lblResultado = findViewById(R.id.lblResultado)
    }

    private fun eventosBotones() {
        btnCalcular.setOnClickListener {
            calcularIMC()
        }

        btnLimpiar.setOnClickListener {
            limpiarCampos()
        }

        btnSalir.setOnClickListener {
            mostrarDialogoSalir()
        }
    }

    private fun calcularIMC() {
        val pesoText = txtPeso.text.toString().trim()
        val estaturaText = txtEstatura.text.toString().trim()

        // Validar campos vacíos
        if (pesoText.isEmpty() || estaturaText.isEmpty()) {
            Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            val peso = pesoText.toDouble()
            val estatura = estaturaText.toDouble()

            // Validar valores lógicos
            if (peso <= 0 || estatura <= 0) {
                Toast.makeText(this, "Los valores deben ser mayores a 0", Toast.LENGTH_SHORT).show()
                return
            }

            if (estatura > 3.0) {
                Toast.makeText(this, "La estatura debe estar en metros (ej: 1.75)", Toast.LENGTH_SHORT).show()
                return
            }

            val imc = peso / (estatura * estatura)

            val clasificacion = when {
                imc < 18.5 -> "Bajo peso"
                imc < 25.0 -> "Peso normal"
                imc < 30.0 -> "Sobrepeso"
                else -> "Obesidad"
            }

            val resultado = "Tu IMC es: %.2f\nClasificación: %s".format(imc, clasificacion)
            lblResultado.text = resultado

        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Ingresa solo números válidos", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Error al calcular el IMC", Toast.LENGTH_SHORT).show()
        }
    }

    private fun limpiarCampos() {
        txtPeso.setText("")
        txtEstatura.setText("")
        lblResultado.text = ""
        Toast.makeText(this, "Campos limpiados", Toast.LENGTH_SHORT).show()
    }

    private fun mostrarDialogoSalir() {
        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
        builder.setTitle("Calculadora IMC")
            .setMessage("¿Deseas salir de la calculadora?")
            .setPositiveButton("Aceptar") { _, _ ->
                finish()
            }
            .setNegativeButton("Cancelar") { _, _ ->
                Toast.makeText(applicationContext, "Operación cancelada", Toast.LENGTH_SHORT).show()
            }
            .setNeutralButton("Quizá") { _, _ ->
                Toast.makeText(applicationContext, "Quizá después", Toast.LENGTH_SHORT).show()
            }
            .show()
    }
}