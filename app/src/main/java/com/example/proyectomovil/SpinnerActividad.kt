package com.example.proyectomovil

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class SpinnerActividad : AppCompatActivity() {

    private lateinit var listViewAlumnos: ListView
    private lateinit var btnSalir: Button
    private lateinit var btnLimpiar: Button
    private lateinit var txtSeleccion: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alumnos_actividad)

        listViewAlumnos = findViewById(R.id.listViewAlumnos)
        btnSalir = findViewById(R.id.btnSalir)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        txtSeleccion = findViewById(R.id.txtSeleccion)

        val alumnos = listOf(
            Alumno(getString(R.string.alumno_jose), "Ing. Tec. Información", "2022030090", R.drawable.joss),
            Alumno(getString(R.string.alumno_carolina), "Ing. Mecatronica", "2022030032", R.drawable.caro),
            Alumno(getString(R.string.alumno_jade), "Ing. Biomedica", "2022030012", R.drawable.jade),
            Alumno(getString(R.string.alumno_daniel), "Ing. Tec. Informacion", "2022030059", R.drawable.daniel),
            Alumno(getString(R.string.alumno_alfredo), "Ing. Tec. Informacion", "2022030077", R.drawable.alfredo),
            Alumno(getString(R.string.alumno_anddy), "Ing. Tec. Informacion", "2022030065", R.drawable.anddy),
            Alumno(getString(R.string.alumno_azul), "Ing. Biomedica", "2022030083", R.drawable.azul),
            Alumno(getString(R.string.alumno_santiago), "Ing. Biomedica", "2022030049", R.drawable.santiago)
        )

        val adapter = AlumnoAdapter(this, alumnos)
        listViewAlumnos.adapter = adapter

        listViewAlumnos.setOnItemClickListener { _, _, position, _ ->
            val seleccionado = alumnos[position]
            txtSeleccion.text = "Seleccionaste a: ${seleccionado.nombre}"
        }

        btnLimpiar.setOnClickListener {
            txtSeleccion.text = ""
        }

        btnSalir.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Salir")
                .setMessage("¿Deseas salir?")
                .setPositiveButton("Aceptar") { _, _ -> finish() }
                .setNegativeButton("Cancelar", null)
                .setNeutralButton("Quizá") { _, _ ->
                    Toast.makeText(this, "Quizá más tarde...", Toast.LENGTH_SHORT).show()
                }
                .show()
        }
    }
}
