package com.example.proyectomovil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class InicioSesion : AppCompatActivity() {

    private lateinit var txtNombre: EditText
    private lateinit var btnEntrar: Button
    private lateinit var btnSalir: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_sesion)

        txtNombre = findViewById(R.id.txtNombre)
        btnEntrar = findViewById(R.id.btnEntrar)
        btnSalir = findViewById(R.id.btnSalir)

        btnEntrar.setOnClickListener {
            val nombre = txtNombre.text.toString().trim()
            if (nombre.isEmpty()) {
                Toast.makeText(this, "Ingrese un nombre", Toast.LENGTH_SHORT).show()
                txtNombre.requestFocus()
            } else {
                val intent = Intent(this, NominaActividad::class.java)
                intent.putExtra("nombre", nombre)
                startActivity(intent)
            }
        }

        btnSalir.setOnClickListener {
            finish()
        }
    }
}
