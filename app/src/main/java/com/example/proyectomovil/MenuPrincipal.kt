package com.example.proyectomovil

import android.widget.Toast
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MenuPrincipal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_principal)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layoutMenu)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun abrirHola(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun abrirIMC(view: View) {
        val intent = Intent(this, ImcActividad::class.java) // CORREGIDO
        startActivity(intent)
    }

    fun abrirConversion(view: View) {
        Toast.makeText(this, "Abrir Conversión", Toast.LENGTH_SHORT).show()
    }

    fun abrirMoneda(view: View) {
        Toast.makeText(this, "Abrir Moneda", Toast.LENGTH_SHORT).show()
    }

    fun abrirCotizacion(view: View) {
        Toast.makeText(this, "Abrir Cotización", Toast.LENGTH_SHORT).show()
    }

    fun abrirSpinner(view: View) {
        Toast.makeText(this, "Abrir Spinner", Toast.LENGTH_SHORT).show()
    }
}
