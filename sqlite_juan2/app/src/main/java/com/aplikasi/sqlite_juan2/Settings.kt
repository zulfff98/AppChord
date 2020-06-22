package com.aplikasi.sqlite_juan2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate

class Settings : AppCompatActivity() {

    private var myswitch: Switch? = null
    private var switchfont: Switch? = null


    override fun onCreate(savedInstanceState: Bundle?) {

        val sharedPreferences2 = getSharedPreferences("FONT", Context.MODE_PRIVATE)
        val Font                       = sharedPreferences2.getString("font","")

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.darkTheme)
        } else {
            setTheme(R.style.AppTheme)
        }

        if (Font=="big"){
            setTheme(R.style.fontTheme  )
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val sharedPreferences = getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE)

        myswitch = findViewById<View>(R.id.myswitch) as Switch
        switchfont = findViewById<View>(R.id.switchfont) as Switch

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            myswitch!!.isChecked = true
        }

        if (Font=="big"){
            switchfont!!.isChecked = true
        }

        myswitch!!.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

                val edit = sharedPreferences.edit()
                edit.putString("set","on")
                edit.apply()

            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

                val edit = sharedPreferences.edit()
                edit.putString("set","off")
                edit.apply()
            }

        }

        switchfont!!.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {

                val ganti = sharedPreferences2.edit()
                ganti.putString("font","big")
                ganti.apply()

                startActivity(Intent(this, MainActivity::class.java))
                finish()

            } else {

                val ganti = sharedPreferences2.edit()
                ganti.putString("font","normal")
                ganti.apply()

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }
}
