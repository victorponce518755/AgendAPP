package ponce.victor.agendapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ponce.victor.agendapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var txtResults: TextView
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        databaseHelper = DatabaseHelper(this)

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        //Obtener datos de la base de datos
        val cursor = databaseHelper.getAllEvents()
        val tasks = mutableListOf<Task>()

        //val tasks = listOf(
        //    Task("Comprar", "10/11/2023", "10:00 AM", "Comprar pan y leche"),
        //    Task("Reunión", "11/11/2023", "02:00 PM", "Reunión con el equipo de desarrollo")
        //)

        // Logs para imprimir las columnas disponibles
        val columnNames = cursor.columnNames
        for (column in columnNames) {
            Log.d("Column Name", column)
        }

        //Procesar y recorrer el cursor
        // Paso 2: Procesar el Cursor y crear la lista de tareas
        if (cursor.moveToFirst()) {
            val nameIndex = cursor.getColumnIndex("nombre")
            val dateIndex = cursor.getColumnIndex("fecha")
            val scheduleIndex = cursor.getColumnIndex("hora")
            val descriptionIndex = cursor.getColumnIndex("descripcion")

            do {
                Log.d("MainActivity", "Iterating through cursor")

                // Log de los índices
                Log.d("MainActivity", "Index of name: $nameIndex")
                Log.d("MainActivity", "Index of date: $dateIndex")
                Log.d("MainActivity", "Index of schedule: $scheduleIndex")
                Log.d("MainActivity", "Index of description: $descriptionIndex")

                // Log de los valores de las columnas
                val name = cursor.getString(nameIndex)
                val date = cursor.getString(dateIndex)
                val schedule = cursor.getString(scheduleIndex)
                val description = cursor.getString(descriptionIndex)

                Log.d("MainActivity", "Task: $name, $date, $schedule, $description")

                tasks.add(Task(name, date, schedule, description))
            } while (cursor.moveToNext())
        }


        recyclerView.adapter = TaskAdapter(tasks)

        val addButton = findViewById(R.id.addButton) as FloatingActionButton
        // val cursor = databaseHelper.getAllEvents()


        // fromColumns = arrayOf("nombre", "fecha", "hora", "descripcion", "lugar")
        // toViews = intArrayOf(R.id.nombre, R.id.fecha, R.id.hora, R.id.descripcion, R.id.lugar)
        // adapter = SimpleCursorAdapter(this, R.layout.activity_main, cursor, fromColumns, toViews, 0)
        // listView.adapter = adapter



        addButton.setOnClickListener {
            val intent = Intent(this, AddEvent::class.java)
            startActivity(intent)
        }
    }
}