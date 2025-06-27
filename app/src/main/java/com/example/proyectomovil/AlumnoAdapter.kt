package com.example.proyectomovil

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class AlumnoAdapter(context: Context, private val alumnos: List<Alumno>) :
    ArrayAdapter<Alumno>(context, 0, alumnos) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemView = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.list_item_alumno, parent, false)

        val alumno = alumnos[position]

        itemView.findViewById<TextView>(R.id.txtNombre).text = alumno.nombre
        itemView.findViewById<TextView>(R.id.txtCarrera).text = alumno.carrera
        itemView.findViewById<TextView>(R.id.txtMatricula).text = alumno.matricula
        itemView.findViewById<ImageView>(R.id.imgAlumno).setImageResource(alumno.fotoId)

        return itemView
    }
}
