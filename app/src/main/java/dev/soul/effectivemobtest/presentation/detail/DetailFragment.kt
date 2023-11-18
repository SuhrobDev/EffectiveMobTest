package dev.soul.effectivemobtest.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.soul.effectivemobtest.R
import dev.soul.effectivemobtest.common.BaseFragment
import dev.soul.effectivemobtest.databinding.FragmentDetailBinding
import dev.soul.effectivemobtest.presentation.detail.adapter.AdapterHotels
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>() {
    private val adapter by lazy {
        AdapterHotels(requireContext())
    }

    private val viewModel: DetailViewModel by viewModels()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailBinding = FragmentDetailBinding.inflate(inflater, container, false)


    override fun created(view: View, savedInstanceState: Bundle?) {
        binding.rv.adapter = adapter

        getRooms()

        adapter.setItemLikeClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_bookingFragment)
        }

        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun getRooms() = lifecycleScope.launch {
        viewModel.getRooms.collectLatest {
            if (it.isLoading)
                binding.progressView.visibility = View.VISIBLE
            else binding.progressView.visibility = View.GONE

            it.data?.let { data ->
                adapter.submitList(data.rooms)
            }
        }
    }
}