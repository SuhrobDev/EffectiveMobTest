package dev.soul.effectivemobtest.presentation.success

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dev.soul.effectivemobtest.R
import dev.soul.effectivemobtest.common.BaseFragment
import dev.soul.effectivemobtest.databinding.FragmentSuccessBinding

class SuccessFragment : BaseFragment<FragmentSuccessBinding>() {

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSuccessBinding = FragmentSuccessBinding.inflate(inflater, container, false)

    override fun created(view: View, savedInstanceState: Bundle?) {
        binding.finish.setOnClickListener {
            findNavController().navigate(R.id.action_successFragment_to_mainFragment)
        }
        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}