package ponce.victor.agendapp

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.sql.Date
import java.sql.Time

// clase para agregar un evento a la base de datos
class AddEvent : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var editTextName: EditText
    private lateinit var editTextDate: EditText
    private lateinit var editTextTime: EditText
    private lateinit var editTextDescription: EditText
    private lateinit var editTextLocation: EditText
    private lateinit var buttonAddEvent: Button

    // metodo para crear la vista de la aplicacion y agregar un evento a la base de datos
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_event)

        databaseHelper = DatabaseHelper(this)
// se obtienen los datos del evento
        editTextName = findViewById(R.id.editTextName)
        editTextDate = findViewById(R.id.editTextDate)
        editTextTime = findViewById(R.id.editTextTime)
        editTextDescription = findViewById(R.id.editTextDescription)
        editTextLocation = findViewById(R.id.editTextLocation)
        buttonAddEvent = findViewById(R.id.buttonAddEvent)


        // metodo para agregar un evento a la base de datos
        fun addEvent() {
            val name = editTextName.text.toString()
            val dateString = editTextDate.text.toString()
            val formatter = SimpleDateFormat("dd/MM/yyyy")
            val date = Date(formatter.parse(dateString).time)

            val timeString = editTextTime.text.toString()
            // Ajusta el formato de la cadena a "HH:mm:ss"
            val adjustedTimeString = "$timeString:00"
            // Intenta convertir el valor a Time
            val time = Time.valueOf(adjustedTimeString)

            val description = editTextDescription.text.toString()
            val location = editTextLocation.text.toString()
            val success = databaseHelper.addEvent(name, date, time, description, location)
// se muestra un mensaje de exito o error al agregar el evento
            if (success) {
                Toast.makeText(this, "Evento agregado", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(this, "Error al agregar evento", Toast.LENGTH_SHORT).show()
            }
        }
// se obtienen los datos del evento
        buttonAddEvent.setOnClickListener() {
            addEvent()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
// boton para regresar a la pantalla principal
        val rtnButton = findViewById<FloatingActionButton>(R.id.rtnButton)

// metodo para regresar a la pantalla principal
        rtnButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }

}