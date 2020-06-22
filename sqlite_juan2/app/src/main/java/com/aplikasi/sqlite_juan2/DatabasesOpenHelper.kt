package com.aplikasi.sqlite_juan2

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteException
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper

class DatabaseOpenHelper(context: Context?) :
    SQLiteAssetHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {

        private const val DATABASE_NAME = "youngpeoplesongdb.db"
        private const val DATABASE_VERSION = 1

        private val TABLE_CONTACTS = "song"
        private val KEY_NOMOR = "nomor_lagu"
        private val KEY_JUDUL = "judul"
    }

    fun lihatLagu():List<SongModelClass>{

        val songList:ArrayList<SongModelClass> = ArrayList<SongModelClass>()
        val selectQuery = "SELECT * FROM $TABLE_CONTACTS "
        val db = this.readableDatabase
        var cursor: Cursor? = null

        try{

            cursor = db.rawQuery(selectQuery, null)

        }catch (e: SQLiteException) {

            db.execSQL(selectQuery)

            return ArrayList()

        }

        var nomorlagu: Int
        var judullagu: String
        var isilagu: String

        if (cursor.moveToFirst()) {
            do {

                nomorlagu = cursor.getInt(cursor.getColumnIndex("nomor_lagu"))
                judullagu = cursor.getString(cursor.getColumnIndex("judul"))
                isilagu   = cursor.getString(cursor.getColumnIndex("isi_lagu"))

                val emp= SongModelClass(nomorlagu = nomorlagu, judullagu = judullagu, isilagu = isilagu)
                songList.add(emp)

            } while (cursor.moveToNext())

        }

        return songList
    }

    fun cariLagu(judul:String):List<SongModelClass>{

        val songList:ArrayList<SongModelClass> = ArrayList<SongModelClass>()
        val selectQuery = "SELECT * FROM $TABLE_CONTACTS WHERE $KEY_JUDUL LIKE '%$judul%' OR $KEY_NOMOR LIKE '%$judul%'"
        val db = this.readableDatabase
        var cursor: Cursor? = null

        try{

            cursor = db.rawQuery(selectQuery, null)

        }catch (e: SQLiteException) {

            db.execSQL(selectQuery)

            return ArrayList()

        }

        var nomorlagu: Int
        var judullagu: String
        var isilagu: String

        if (cursor.moveToFirst()) {
            do {

                nomorlagu = cursor.getInt(cursor.getColumnIndex("nomor_lagu"))
                judullagu = cursor.getString(cursor.getColumnIndex("judul"))
                isilagu   = cursor.getString(cursor.getColumnIndex("isi_lagu"))

                val emp= SongModelClass(nomorlagu = nomorlagu, judullagu = judullagu, isilagu = isilagu)

                songList.add(emp)

            } while (cursor.moveToNext())

        }

        return songList
    }

}
