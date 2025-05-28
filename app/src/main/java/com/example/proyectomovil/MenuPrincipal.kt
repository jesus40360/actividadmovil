package com.example.proyectomovil

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
        val intent = Intent(this, ImcActividad::class.java)
        startActivity(intent)
    }

    fun abrirConversion(view: View) {
        val intent = Intent(this, ConversionActividad::class.java)
        startActivity(intent)
    }

    fun abrirMoneda(view: View) {
        val intent = Intent(this, MonedaActividad::class.java)
        startActivity(intent)
    }

    fun abrirCotizacion(view: View) {
        val intent = Intent(this, ClienteActividad::class.java)
        startActivity(intent)
    }

    fun abrirSpinner(view: View) {
        // En caso de tener SpinnerActividad implementada
        // val intent = Intent(this, SpinnerActividad::class.java)
        // startActivity(intent)
    }
}
