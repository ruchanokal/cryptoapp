package com.ruchanokal.cryptoapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.ruchanokal.cryptoapp.R


abstract class BaseFragment<VB : ViewBinding, VM : ViewModel>(
    private val bindingInflater: (inflater: LayoutInflater) -> VB
)    : Fragment() {

    private var _binding: VB? = null
    protected val binding: VB get() = _binding as VB

    protected abstract val viewModel: VM
    protected abstract fun onCreateFinished()
    protected abstract fun observeEvents()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)

        if (_binding == null) {
            throw IllegalArgumentException("Binding is null")
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onCreateFinished()
        observeEvents()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}