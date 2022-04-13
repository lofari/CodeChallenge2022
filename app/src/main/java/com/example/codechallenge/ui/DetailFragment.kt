package com.example.codechallenge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.codechallenge.databinding.FragmentDetailBinding
import com.example.codechallenge.model.Character
import com.example.codechallenge.viewmodel.DetailViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<DetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDetailObserver()
    }

    private fun setDetailObserver() {
        viewModel.detail.observe(this, {
            setContent(it)
        })
    }

    private fun setImageFromUrl(image: String) {
        binding.loading.pauseAnimation()
        binding.loading.visibility = View.GONE
        Picasso.get().load(image).into(binding.imageView)
        fadeIn(binding.imageView)

    }

    private fun fadeIn(view: View) {
        view.animate().apply {
            alpha(1f)
            duration = 500
        }.start()
    }

    private fun setContent(character: Character) {
        with(character) {
            setImageFromUrl(image)
            binding.name.text = name
            binding.species.text = species
            binding.status.text = status
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
