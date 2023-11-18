package dev.soul.effectivemobtest.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<Binding : ViewBinding> : Fragment() {

    protected lateinit var binding: Binding
    protected lateinit var dataBinding: ViewDataBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return if (view != null) view
        else {
            binding = createBinding(inflater, container)
            binding.root
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        created(view, savedInstanceState)
//        loadObserver()
    }

    abstract fun createBinding(inflater: LayoutInflater, container: ViewGroup?): Binding

    abstract fun created(view: View, savedInstanceState: Bundle?)

    fun showToast(str: String) {
//        requireContext().showToast(str)
    }

}