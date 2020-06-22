package com.aplikasi.sqlite_juan2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.vokasi.sqlite_juan2.Adapter
import kotlinx.android.synthetic.main.activity_daftar_isi.listView
import kotlinx.android.synthetic.main.activity_search.*

class Search : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPreferences2 = getSharedPreferences("FONT", Context.MODE_PRIVATE)
        val Font                       = sharedPreferences2.getString("font","")

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.darkTheme)
        } else setTheme(R.style.AppTheme)

        if (Font=="big"){
            setTheme(R.style.fontTheme )
        } else
            setTheme(R.style.fontTheme2)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val sharedPreferences = getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE)
        val setting                       = sharedPreferences.getString("set","")

        if (setting=="on") {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener {

            val intent = Intent(this, Daftarisi::class.java)
            val intent2  = Intent(this, Chord::class.java)
            val intent3  = Intent(this, Settings::class.java)

            when (it.itemId) {
                R.id.nav_daftarlagu ->  startActivity(intent)

                R.id.nav_chord ->  startActivity(intent2)

                R.id.nav_settings ->  startActivity(intent3)
            }

            drawer!!.closeDrawer(GravityCompat.START)
           true
        }

        val toggle =
            ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close
            )
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        cari.setOnClickListener {

            val cari = this.judul.getText().toString()

            val databaseHandler = DatabaseOpenHelper(this)
            val context = this

            val song: List<SongModelClass> = databaseHandler.cariLagu(cari)

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
}
