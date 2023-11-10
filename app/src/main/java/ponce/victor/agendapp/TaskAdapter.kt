package ponce.victor.agendapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import ponce.victor.agendapp.databinding.TaskItemBinding

class TaskAdapter(context: Context, private val tasks: List<Task>) : ArrayAdapter<Task>(context, 0, tasks) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View{
        val binding = if (convertView == null) {
            // Inflate a new view if convertView is null
            TaskItemBinding.inflate(LayoutInflater.from(context), parent, false)
        } else {
            // Otherwise, reuse the convertView
            TaskItemBinding.bind(convertView)
        }

        val task = getItem(position)

        binding.tskName.text = task?.name
        binding.tskDate.text = task?.date
        binding.tskSchedule.text = task?.schedule
        binding.tskDescription.text = task?.description

        return binding.root

    }

}