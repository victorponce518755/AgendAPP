package ponce.victor.agendapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.sql.Time
import java.sql.Date

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASENAME, null, DATABASEVERSION)
{

    companion object{
        private val DATABASENAME = "Agenda.db"
        private val DATABASEVERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY = "CREATE TABLE eventos (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, fecha INTEGER, hora INTEGER, descripcion TEXT, lugar TEXT)"
        db?.execSQL(CREATE_TABLE_QUERY)
    }


    override  fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS eventos ")
        onCreate(db)
    }


    // si no nos acepta el tipo de dato date y time, hay que hacer modificaciones aqui
    fun addEvent(nombre: String, fecha: Date, hora: Time, descripcion: String, lugar: String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("nombre", nombre)
        values.put("fecha", fecha.time)
        values.put("hora", hora.time)
        values.put("descripcion", descripcion)
        values.put("lugar", lugar)
        val result = db.insert("eventos", null, values)

        db.close()
        return result != -1L
    }

    fun getAllEvents() : Cursor {
        val db = this.writableDatabase
        return db.rawQuery("SELECT * FROM eventos", null)
    }

    fun updateEvent(id: String, nombre: String, fecha: Date, hora: Time, descripcion: String, lugar: String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("nombre", nombre)
        values.put("fecha", fecha.time)
        values.put("hora", hora.time)
        values.put("descripcion", descripcion)
        values.put("lugar", lugar)
        val result = db.update("eventos", values, "id=?", arrayOf(id))

        db.close()
        return result > 0
    }


    fun deleteEvent(id: String):Boolean{
        val db = this.writableDatabase
        val result = db.delete("eventos", "id=?", arrayOf(id.toString()))

        db.close()
        return result > 0
    }

}