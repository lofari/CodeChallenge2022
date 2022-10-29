package com.example.codechallenge.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.codechallenge.R
import com.example.codechallenge.databinding.FragmentWelcomeBinding
import com.example.codechallenge.ui.extension.typeWrite
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButtonClickListener()
        setAnimationClickListener()
        setTypeWriterEffect()
    }

    private fun setTypeWriterEffect() {
        binding.animationView.addLottieOnCompositionLoadedListener {
            binding.welcomeTitle.isVisible = true
            binding.welcomeTitle.bringToFront()

            activity?.lifecycleScope?.launch {

            }
            binding.welcomeTitle.typeWrite(viewLifecycleOwner, listOf("Psst...   ","Click Me!") , 150L)

        }

    }


    private fun setAnimationClickListener() {
        binding.animationView.setOnClickListener {
            if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    private fun setButtonClickListener() {
        binding.startButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_listFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}