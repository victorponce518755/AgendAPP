package ponce.victor.agendapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var txtResults: TextView
    private  lateinit var listView: ArrayAdapter<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseHelper = DatabaseHelper(this)

        val listView = findViewById<ListView>(R.id.list_view)
        val addButton = findViewById(R.id.addButton) as FloatingActionButton
        val cursor = databaseHelper.getAllEvents()


        //val fromColumns = arrayOf("nombre", "fecha", "hora", "descripcion", "lugar")
        //val toViews = intArrayOf(R.id.nombre, R.id.fecha, R.id.hora, R.id.descripcion, R.id.lugar)
        //val adapter = SimpleCursorAdapter(this, R.layout.activity_main, cursor, fromColumns, toViews, 0)
        //listView.adapter = adapter



        addButton.setOnClickListener {
            val intent = Intent(this, AddEvent::class.java)
            startActivity(intent)
        }


    }
}