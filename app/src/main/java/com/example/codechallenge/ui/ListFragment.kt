package com.example.codechallenge.ui

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.codechallenge.common.Constants
import com.example.codechallenge.R
import com.example.codechallenge.viewmodel.ListViewModel
import com.example.codechallenge.adapter.OnImageClickListener
import com.example.codechallenge.adapter.PictureAdapter
import com.example.codechallenge.databinding.FragmentListBinding
import com.example.codechallenge.model.Character
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(),
    OnImageClickListener {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<ListViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPicturesObserver()
        setLayoutManager()
    }

    private fun setLayoutManager() {
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.list.layoutManager = GridLayoutManager(context, 4)
        } else {
            binding.list.layoutManager = GridLayoutManager(context, 3)
        }
    }

    private fun setPicturesObserver() {
        viewModel.pictureList.observe(this, {
            binding.loading.visibility = View.GONE
            binding.loading.pauseAnimation()
            initList(it)
        })
    }

    private fun initList(imageList: List<Character>) {
        val adapter =
            PictureAdapter(imageList, this)
        binding.list.adapter = adapter
        binding.list.isClickable = true
    }

    override fun onItemClick(item: Character, position: Int) {
        binding.list.isClickable = false
        val bundle = bundleOf(Constants.CACHE_KEY to item.id.toString())
        findNavController().navigate(R.id.action_listFragment_to_detailFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
