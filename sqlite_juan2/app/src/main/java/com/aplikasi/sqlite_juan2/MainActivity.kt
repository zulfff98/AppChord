package com.aplikasi.sqlite_juan2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.vokasi.sqlite_juan2.Adapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context = this

        discover.setOnClickListener {
            val intent = Intent(context,Search::class.java)
            startActivity(intent)

        }

    }
    override fun onBackPressed() {
        super.onBackPressed()
    }

}