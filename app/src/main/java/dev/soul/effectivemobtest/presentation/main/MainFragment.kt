package dev.soul.effectivemobtest.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.soul.effectivemobtest.R
import dev.soul.effectivemobtest.common.BaseFragment
import dev.soul.effectivemobtest.databinding.FragmentMainBinding
import dev.soul.effectivemobtest.presentation.main.adapter.PeculiaritiesAdapter
import dev.soul.effectivemobtest.presentation.main.adapter.ViewPagerAdapter
import dev.soul.effectivemobtest.utils.autoScroll
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {
    private val adapterImage by lazy {
        ViewPagerAdapter()
    }

    private val adapterPeculiarities by lazy {
        PeculiaritiesAdapter()
    }

    private val viewModel: MainViewModel by viewModels()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainBinding =
        FragmentMainBinding.inflate(inflater, container, false)

    override fun created(view: View, savedInstanceState: Bundle?) {
        binding.viewPager.adapter = adapterImage
        binding.list.adapter = adapterPeculiarities

        binding.dotsIndicator.attachTo(binding.viewPager)
        binding.viewPager.autoScroll(4000)

        getHotel()

        binding.next.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_detailFragment)
        }


    }

    private fun getHotel() = lifecycleScope.launch {
        viewModel.getHotel.collectLatest {
            if (it.isLoading)
                binding.progressView.visibility = View.VISIBLE
            else binding.progressView.visibility = View.GONE

            it.data?.let { data ->
                binding.hotel = data
                Log.e("Main_fragment", "getHotel: ${data.image_urls}")
                adapterImage.submitList(data.image_urls)
                adapterPeculiarities.setList(data.about_the_hotel.peculiarities)

                binding.apply {

                    rating.text =
                        getString(R.string.rating, data.rating.toString(), data.rating_name)
                    name.text = data.name
                    address.text = data.adress
                    money.text = getString(R.string.begin_money, data.minimal_price.toString())
                    forTour.text = data.price_for_it

                    description.text = data.about_the_hotel.description
                }

            }

            if (it.error.isNotBlank()) {
                Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}