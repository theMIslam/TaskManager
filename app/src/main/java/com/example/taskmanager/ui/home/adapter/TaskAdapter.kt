package com.example.taskmanager.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.taskmanager.databinding.ItemTaskBinding
import com.example.taskmanager.ui.model.Task
import kotlin.reflect.KFunction1

private val <P1, R> KFunction1<P1, R>.size: Int
    get() {
        TODO("Not yet implemented")
    }

class TaskAdapter(private val data: KFunction1<Task, Unit>, kFunction1: (Task) -> Unit) :
    Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size
    fun addTasks(data: List<Task>) {

    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) : ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.tvTittle.text = task.tittle
            binding.tvDesc.text = task.desc
        }
    }

}

private operator fun <P1, R> KFunction1<P1, R>.get(position: Int): P1 {
    TODO("Not yet implemented")
}
