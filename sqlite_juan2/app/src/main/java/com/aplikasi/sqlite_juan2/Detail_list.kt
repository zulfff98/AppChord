package com.aplikasi.sqlite_juan2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.detail_list.*

class Detail_list : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.darkTheme)
        } else setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_list)

        val sharedPreferences = getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE)
        val setting                       = sharedPreferences.getString("set","")

        if (setting=="on") {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        }

        val intent = intent
        val songArr = intent.getStringExtra("songArrayNomor")

        val databaseAccess: DatabaseAccess? = DatabaseAccess.getInstance(applicationContext)
        databaseAccess!!.open()

        val isi: String = databaseAccess.getIsi(songArr)
        textViewDetail.setText(isi)
        databaseAccess.close()

    }
}

