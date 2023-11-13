package ponce.victor.agendapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
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
    }

    // metodo para obtener el numero de eventos en la base de datos
    override fun getItemCount(): Int {
        return tasks.size
    }


}