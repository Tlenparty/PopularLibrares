package com.geekbrains.popularlibraries.framework.ui.view.converter_fragment

import android.content.Intent
import android.content.Intent.ACTION_GET_CONTENT
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import moxy.MvpAppCompatFragment
import com.geekbrains.popularlibrares.R
import com.geekbrains.popularlibrares.databinding.FragmentConverterBinding
import com.geekbrains.popularlibraries.extentions.click
import com.geekbrains.popularlibraries.model.converter.ConverterFactory
import com.geekbrains.popularlibraries.presentation.ConverterPresenter
import com.geekbrains.popularlibraries.scheduler.SchedulersFactory
import moxy.ktx.moxyPresenter

class ConverterFragment : MvpAppCompatFragment(), ConverterView {

    private val presenter by moxyPresenter {
        ConverterPresenter(
            converter = ConverterFactory.create(requireContext()),
            schedulers = SchedulersFactory.create()
        )
    }

    private var _binding: FragmentConverterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConverterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().title = getString(R.string.converter_title)
        binding.button.click(::pickImage)
    }

    private fun pickImage() {
        val getIntent = Intent(ACTION_GET_CONTENT)
        getIntent.type = "image/*"

        startActivityForResult(getIntent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        data?.data?.let(presenter::convert)
            ?: Toast.makeText(requireContext(), "Изображение не выбрано", Toast.LENGTH_SHORT).show()
    }

    override fun showContent(uri: Uri?) = with(binding) {
        val bitmap: Bitmap? =
            uri?.let { MediaStore.Images.Media.getBitmap(requireContext().contentResolver, uri) }

        progress.visibility = View.GONE
        imageView.setImageBitmap(bitmap)

        button.click(::pickImage)
        button.text = getString(R.string.select_image)
    }

    override fun showLoading() = with(binding) {
        progress.visibility = View.VISIBLE

        button.click(presenter::cancel)
        button.text = getString(R.string.cancel)
    }

    override fun showError(error: Throwable) {
        Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance(): Fragment = ConverterFragment()
    }

}