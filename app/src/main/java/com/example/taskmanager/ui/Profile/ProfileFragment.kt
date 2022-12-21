package com.example.taskmanager.ui.Profile

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
import com.example.taskmanager.data.Pref
import com.example.taskmanager.databinding.FragmentProfileBinding
import com.example.taskmanager.utils.loadImage

@Suppress("DEPRECATION")
class ProfileFragment : Fragment() {

    private lateinit var pref: Pref

    private val launcher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK
            && result.data != null
        ) {
            val photoUri: Uri? = result.data?.data
            binding.ivProfile.loadImage(photoUri.toString())
            pref.saveImage(photoUri.toString())
        }
    }

    private val IMAGE_GALLERY_REQUEST_CODE: Int = 555
    private lateinit var _binding: FragmentProfileBinding

    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        pref = Pref(requireContext())


        binding.ivProfile.loadImage(pref.getImage())
        binding.etUserName.setText(pref.getName())
        binding.etUserName.addTextChangedListener {
            pref.saveName(binding.etUserName.text.toString())
        }

        binding.ivProfile.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            launcher.launch(intent)
        }

        binding.etUserAge.setText(pref.getAge())
        binding.etUserAge.addTextChangedListener {
            pref.setAge(binding.etUserAge.text.toString())
        }
        binding.etUserGen.setText(pref.getGen())
        binding.etUserGen.addTextChangedListener {
            pref.setGen(binding.etUserGen.text.toString())
        }
        binding.etUserName.setText(pref.getName())
        binding.etUserName.addTextChangedListener {
            pref.setName(binding.etUserName.text.toString())
        }

        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListener()
    }

    private fun clickListener() {
        _binding.ivProfile.setOnClickListener {
            openFile()
        }
        _binding.etUserName.setOnClickListener {
            _binding.etContainer.visibility = View.VISIBLE
        }
    }

    private fun openFile() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(gallery, IMAGE_GALLERY_REQUEST_CODE)
    }


    @Deprecated("Deprecated in Java")
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_GALLERY_REQUEST_CODE) {
            if (resultCode == RESULT_OK && requestCode == IMAGE_GALLERY_REQUEST_CODE) {
                val imageUri = data?.data
                _binding.ivProfile.setImageURI(imageUri)
            }
        }
    }

}