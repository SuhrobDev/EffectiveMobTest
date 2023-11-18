package dev.soul.effectivemobtest.presentation.booking

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
import dev.soul.effectivemobtest.databinding.FragmentBookingBinding
import dev.soul.effectivemobtest.presentation.booking.adapter.AdapterAdditional
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BookingFragment : BaseFragment<FragmentBookingBinding>() {
    private val adapter by lazy {
        AdapterAdditional()
    }

    private val viewModel: BookingViewModel by viewModels()


    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentBookingBinding = FragmentBookingBinding.inflate(inflater, container, false)

    override fun created(view: View, savedInstanceState: Bundle?) {
        binding.rv.adapter = adapter

        val list = listOf(
            "Первый турист",
            "Второй турист",
            "Tретий турист",
            "Четвертый турист",
            "Пятый турист",
            "Шестой турист",
            "Седьмой турист",
            "Восьмой турист",
            "Девятый турист",
            "Десятый турист",
        )

        var pos = 0

        binding.add.setOnClickListener {
            adapter.addItem(list[pos])
            pos++
        }

        getBookingData()

        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.totalPrice.setOnClickListener {
            findNavController().navigate(R.id.successFragment)
        }
    }


    private fun getBookingData() = lifecycleScope.launch {
        viewModel.getBookingData.collectLatest {
            if (it.isLoading)
                binding.progressView.visibility = View.VISIBLE
            else binding.progressView.visibility = View.GONE

            it.data?.let { data ->
                binding.apply {
                    val total = data.tour_price + data.fuel_charge + data.service_charge

                    rating.text =
                        getString(R.string.rating, data.horating.toString(), data.rating_name)
                    name.text = data.hotel_name
                    address.text = data.hotel_adress

                    from.text = data.departure
                    city.text = data.arrival_country
                    date.text = getString(R.string.date, data.tour_date_start, data.tour_date_stop)
                    days.text = getString(R.string.days, data.number_of_nights.toString())
                    hotel.text = data.hotel_name
                    number.text = data.room
                    charge.text = data.nutrition
                    tourCharge.text = getString(R.string.money, data.tour_price.toString())
                    fuelCharge.text = getString(R.string.money, data.fuel_charge.toString())
                    serviceCharge.text = getString(R.string.money, data.service_charge.toString())
                    totalCharge.text = getString(R.string.money, total.toString())
                    totalPrice.text = getString(R.string.money, total.toString())
                }
            }


        }
    }
}