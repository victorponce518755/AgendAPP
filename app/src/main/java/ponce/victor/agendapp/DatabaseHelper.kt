package ponce.victor.agendapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.sql.Time
import java.sql.Date


// clase para crear la base de datos
class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASENAME, null, DATABASEVERSION) {

    companion object {
        private val DATABASENAME = "Agenda.db"
        private val DATABASEVERSION = 1
    }

    // metodo para crear la tabla eventos con sus respectivos campos
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY =
            "CREATE TABLE eventos (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, fecha INTEGER, hora INTEGER, descripcion TEXT, lugar TEXT)"
        db?.execSQL(CREATE_TABLE_QUERY)
    }

    // metodo para actualizar la tabla eventos
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS eventos ")
        onCreate(db)
    }

    // metodo para agregar un evento a la base de datos
    // si no nos acepta el tipo de dato date y time, hay que hacer modificaciones aqui
    fun addEvent(
        nombre: String,
        fecha: Date,
        hora: Time,
        descripcion: String,
        lugar: String
    ): Boolean {
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

    // metodo para obtener todos los eventos de la base de datos
    fun getAllEvents(): Cursor {
        val db = this.writableDatabase
        return db.rawQuery("SELECT * FROM eventos", null)
    }

    // metodo para obtener un evento en especifico de la base de datos
    fun updateEvent(
        id: String,
        nombre: String,
        fecha: Date,
        hora: Time,
        descripcion: String,
        lugar: String
    ): Boolean {
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

    // metodo para eliminar un evento de la base de datos
    fun deleteEvent(id: String): Boolean {
        val db = this.writableDatabase
        val result = db.delete("eventos", "id=?", arrayOf(id.toString()))

        db.close()
        return result > 0
    }

    // metodo para eliminar un evento de la base de datos por su nombre
    fun deleteEventByName (name: String): Boolean {
        if (name.isNotBlank()) {
            val db = this.writableDatabase
            val result = db.delete("eventos", "nombre=?", arrayOf(name))

            db.close()
            return result > 0
        }
        return false
    }

}