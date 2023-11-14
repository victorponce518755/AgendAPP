package ponce.victor.agendapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ponce.victor.agendapp.databinding.TaskItemBinding
import java.text.FieldPosition


class TaskAdapter(private var tasks: List<Task>) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    // clase para crear la vista de los eventos
    class TaskViewHolder(val binding: TaskItemBinding) : RecyclerView.ViewHolder(binding.root)

    // metodo para crear la vista de los eventos en el recycler view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = TaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.binding.tskName.text = task.name
        holder.binding.tskDate.text = "Fecha: " + task.date
        holder.binding.tskSchedule.text = "Hora: " + task.schedule
        holder.binding.tskDescription.text = "Descripción: " + task.description
        holder.binding.tskLocation.text = "Ubicación: " + task.location

        // Agregar un listener para abrir Google Maps
        holder.binding.tskLocation.setOnClickListener {
            openGoogleMaps(task.location, holder.binding.tskLocation.context)
        }
    }

    // metodo para obtener el numero de eventos en la base de datos
    override fun getItemCount(): Int {
        return tasks.size
    }

    // Método para abrir Google Maps, con la ubicación del evento
    fun openGoogleMaps(location: String, context: Context) {
        val mapIntentUri: Uri = Uri.parse("http://maps.google.com/maps?q=$location")
        val mapIntent = Intent(Intent.ACTION_VIEW, mapIntentUri)


        // Verificar si hay una aplicación que pueda manejar el Intent
        if (mapIntent.resolveActivity(context.packageManager) != null) {

            context.startActivity(mapIntent)
        } else {
            // Manejar el caso en que Google Maps no esté instalado

            Toast.makeText(context, "Google Maps no está instalado.", Toast.LENGTH_LONG).show()
        }
    }




}