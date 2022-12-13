package com.example.taskmanager.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.taskmanager.databinding.ItemTaskBinding
import com.example.taskmanager.ui.model.Task

class TaskAdapter(private val data:ArrayList<Task>):
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(ItemTaskBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(data.get(position))
    }

    override fun getItemCount(): Int {
        return data.size
    }

inner class TaskViewHolder(private val  binding: ItemTaskBinding):
    RecyclerView.ViewHolder(binding.root) {
    fun bind(task: Task) {
        binding.tvTittle.text=task.tittle
        binding.tvDesc.text=task.desc
    }



}