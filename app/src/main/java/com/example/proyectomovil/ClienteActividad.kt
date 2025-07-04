package com.example.proyectomovil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ClienteActividad : AppCompatActivity() {

    private lateinit var txtCliente: EditText
    private lateinit var btnEntrar: Button
    private lateinit var btnRegresar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cliente_actividad)

        txtCliente = findViewById(R.id.txtCliente)
        btnEntrar = findViewById(R.id.btnEntrar)
        btnRegresar = findViewById(R.id.btnRegresar)

        btnEntrar.setOnClickListener {
            val cliente = txtCliente.text.toString().trim()
            if (cliente.isEmpty()) {
                Toast.makeText(this, "Falto capturar el nombre del cliente", Toast.LENGTH_SHORT).show()
                txtCliente.requestFocus()
            } else {
                val intent = Intent(this, CotizacionActividad::class.java)
                intent.putExtra("cliente", cliente)
                startActivity(intent)
            }
        }

        btnRegresar.setOnClickListener {
            finish()
        }
    }
}
