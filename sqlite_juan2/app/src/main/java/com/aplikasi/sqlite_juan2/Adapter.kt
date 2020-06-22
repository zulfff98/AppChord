package com.vokasi.sqlite_juan2

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.aplikasi.sqlite_juan2.R


class Adapter(private val context: Activity, private val nomor: Array<String>,
              private val judul: Array<String>, private val isi: Array<String>
)
    : ArrayAdapter<String>(context, R.layout.custom_lsit, judul) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_lsit, null, true)

        val idNomor = rowView.findViewById(R.id.textViewNomor) as TextView
        val Judul = rowView.findViewById(R.id.textViewjudul) as TextView

        idNomor.text = nomor[position]
        Judul.text   = judul[position]

        return rowView

    }
}