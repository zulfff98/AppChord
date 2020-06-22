package com.aplikasi.sqlite_juan2

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseAccess private constructor(context: Context) {
    private val openHelper: SQLiteOpenHelper
    private var db: SQLiteDatabase? = null
    var c: Cursor? = null
    fun open() {
        db = openHelper.writableDatabase
    }

    fun close() {
        if (db != null) {
            db!!.close()
        }
    }

    fun getIsi(nomor : String): String {

        var c = this.c
        c = db!!.rawQuery("select isi_lagu from song where nomor_lagu = '$nomor' ", arrayOf())

        val buffer = StringBuffer()

        while (c.moveToNext()) {
            val isi = c.getString(0)
            buffer.append("" + isi)
        }
        return buffer.toString()
    }

    companion object {
        private var instance: DatabaseAccess? = null

        fun getInstance(context: Context): DatabaseAccess? {
            if (instance == null) {
                instance = DatabaseAccess(context)
            }
            return instance
        }
    }

    init {
        openHelper = DatabaseOpenHelper(context)
    }
}