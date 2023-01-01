package com.example.taskmanager.ui.home

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentHomeBinding
import com.example.taskmanager.ui.home.adapter.TaskAdapter
import com.example.taskmanager.ui.model.Task


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var adapter: TaskAdapter
    private lateinit var data: List<Task>


    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = TaskAdapter(this::onLongClick, this::onClick)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvTasks.adapter = adapter
        setData()
        clickListener()
    }

    private fun clickListener() {
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.navigation_task)
        }
    }

    private fun setData() {
        data = App.db.dao().getAll()
        adapter.addTasks(data)
    }


    private fun onLongClick(task: Task) {
        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle("Delete")
        builder.setMessage("Are you sure you want to delete this note?")

        builder.setPositiveButton("Yes") { _: DialogInterface, _: Int ->
            App.db.dao().delete(task)
            setData()
        }
        builder.setNegativeButton("No") { _: DialogInterface, _: Int ->
        }
        builder.show()
    }

    private fun onClick(task: Task) {
        findNavController().navigate(R.id.navigation_task, bundleOf(KEY_FOR_TASK to task))
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val KEY_FOR_TASK = "task"
    }
}