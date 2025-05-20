package com.example.proyectomovil

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // declaración de los Objetos
    private lateinit var lblSaludar: TextView
    private lateinit var txtNombre: EditText
    private lateinit var btnSaludo: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnCerrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        IniciarComponentes()
        eventosBotones()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun IniciarComponentes() {
        lblSaludar = findViewById(R.id.lblSaludar)
        txtNombre = findViewById(R.id.txtNombre)
        btnSaludo= findViewById(R.id.btnSaludo)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnCerrar = findViewById(R.id.btnCerrar)
    }

    private fun eventosBotones() {
        btnSaludo.setOnClickListener {
            val nombre = txtNombre.text.toString()

            if (nombre.isEmpty()) {
                Toast.makeText(applicationContext, "Falto capturar el Nombre", Toast.LENGTH_SHORT).show()
            } else {
                val saludo = "Hola $nombre ¿Cómo Estás?"
                lblSaludar.text = saludo
            }
        }

        btnLimpiar.setOnClickListener {
            txtNombre.setText("")
            lblSaludar.setText("")
        }

        btnCerrar.setOnClickListener {
            val builder = androidx.appcompat.app.AlertDialog.Builder(this)
            builder.setTitle("App Hola")
                .setMessage("¿Deseas salir de la aplicación?")
                .setPositiveButton("Aceptar") { _, _ ->
                    finish()
                }
                .setNegativeButton("Cancelar") { _, _ ->
                    Toast.makeText(applicationContext, "Cancelar", Toast.LENGTH_SHORT).show()
                }
                .setNeutralButton("Quizá") { _, _ ->
                    Toast.makeText(applicationContext, "Quizá", Toast.LENGTH_SHORT).show()
                }
                .show()
        }
    }
}