package com.example.taskmanager.ui.Profile

import Pref
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.taskmanager.databinding.FragmentProfileBinding

import com.example.taskmanager.utils.loadImage
import com.example.taskmanager.ui.extension.loadImage as loadImage1

class ProfileFragment : Fragment() {

    private lateinit var pref: Pref
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListener()
    }

    private fun clickListener() {
        pref = Pref(requireContext())
        //profile photo
        binding.ivProfile.loadImage1(pref.getImage())
        binding.ivProfile.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            launcher.launch(intent)
        }
        //edit text
        binding.etUserAge.setText(pref.getAge())
        binding.etUserAge.addTextChangedListener {
            pref.setAge(binding.etUserAge.text.toString())
        }
        //edit text
        binding.etUserGen.setText(pref.getGen())
        binding.etUserGen.addTextChangedListener {
            pref.setGen(binding.etUserGen.text.toString())
        }
        //edit text
        binding.etUserName.setText(pref.getName())
        binding.etUserName.addTextChangedListener {
            pref.setName(binding.etUserName.text.toString())
        }
    }

    //set profile photo
    private val launcher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK
            && result.data != null
        ) {
            val photoUri: Uri? = result.data?.data
            pref.setImage(photoUri.toString())
            binding.run {
                pref.setImage(photoUri.toString())
                ivProfile.run { loadImage1(photoUri.toString()) }
            }
        }
    }
}