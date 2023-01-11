package com.example.taskmanager.ui.task

import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentTaskBinding
import com.example.taskmanager.ui.home.HomeFragment
import com.example.taskmanager.ui.model.Task

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private var task: Task? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        @Suppress("DEPRECATION")
        task = arguments?.getSerializable(HomeFragment.KEY_FOR_TASK) as Task?
        if (task == null) {
            binding.btnSave.text = getString(R.string.save)
        } else {
            binding.etTittle.setText(task?.tittle.toString())
            binding.etDesc.setText(task?.desc.toString())
            binding.btnSave.text = getString(R.string.update)
        }
        binding.btnSave.setOnClickListener {
            when (task) {
                null -> {
                    save()
                    saveDataToFb()
                }
                else -> {
                    update()
                }
            }
            findNavController().navigateUp()
        }
    }

    private fun saveDataToFb() {
        val data = Task(
            tittle = binding.etTittle.text.toString(),
            desc = binding.etDesc.text.toString()
        )
        App.firebaseDB?.collection("tasks")?.add(data)
            ?.addOnSuccessListener {
                Log.e("ololo", "aaa")
            }
            ?.addOnFailureListener {
                showToast(it.message.toString())
            }
    }

    private fun showToast(toString: String) {
        TODO("Not yet implemented")
    }


    private fun update() {
        task?.tittle = binding.etTittle.text.toString()
        task?.desc = binding.etDesc.text.toString()
        task?.let { App.db.dao().update(it) }
    }

    private fun save() {
        val data = Task(
            tittle = binding.etTittle.text.toString(),
            desc = binding.etDesc.text.toString()
        )
        App.db.dao().insert(data)
    }
}