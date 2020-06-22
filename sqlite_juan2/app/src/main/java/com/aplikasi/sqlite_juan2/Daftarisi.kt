package com.aplikasi.sqlite_juan2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.vokasi.sqlite_juan2.Adapter
import kotlinx.android.synthetic.main.activity_daftar_isi.*

class Daftarisi : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPreferences2 = getSharedPreferences("FONT", Context.MODE_PRIVATE)
        val Font                       = sharedPreferences2.getString("font","")

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.darkTheme)
        } else setTheme(R.style.AppTheme)

        if (Font=="big"){
            setTheme(R.style.fontTheme )
        }

        if (Font=="normal"){
            setTheme(R.style.fontTheme2 )
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_isi)

        val sharedPreferences = getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE)
        val setting                       = sharedPreferences.getString("set","")

        if (setting=="on") {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        viewLagu()
    }

    private fun viewLagu(){

        val databaseHandler = DatabaseOpenHelper(this)
        val context = this

        val song: List<SongModelClass> = databaseHandler.lihatLagu()

        val songArrayNomor = Array(song.size){"null"}
        val songArrayJudul = Array(song.size){"null"}
        val songArrayIsi = Array(song.size){"null"}

        for((index, e) in song.withIndex()){
            songArrayNomor[index] = e.nomorlagu.toString()
            songArrayJudul[index] = e.judullagu
            songArrayIsi[index]   = e.isilagu

        }

        val myListAdapter = Adapter(this,songArrayNomor,songArrayJudul,songArrayIsi)
        listView.adapter  = myListAdapter

        listView.setOnItemClickListener { parent, view, position, id ->

            val songArr = songArrayNomor[position]

            val intent = Intent(context,Detail_list::class.java)
            intent.putExtra("songArrayNomor", songArr)
            startActivity(intent)

        }

    }

}
