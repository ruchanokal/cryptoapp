package com.ruchanokal.cryptoapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ruchanokal.cryptoapp.R
import com.ruchanokal.cryptoapp.databinding.FragmentMainBinding
import com.ruchanokal.cryptoapp.model.cyrptos.Data
import com.ruchanokal.cryptoapp.ui.BaseFragment
import com.ruchanokal.cryptoapp.ui.adapter.CryptoAdapter
import com.ruchanokal.cryptoapp.util.Constants.API_KEY
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding,MainViewModel>(FragmentMainBinding::inflate) {


    override val viewModel by viewModels<MainViewModel>()
    private lateinit var cryptoAdapter: CryptoAdapter
    private val dataList = arrayListOf<Data>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getCryptoData(apiKey = API_KEY)
        cryptoAdapter = CryptoAdapter(dataList)

    }

    override fun onCreateFinished() {
    }

    override fun observeEvents() {

        with(viewModel){

            isLoading.observe(viewLifecycleOwner, Observer {
                binding.progressBar.isVisible = it
            })

            cryptoList.observe(viewLifecycleOwner,Observer{
                it?.let {
                    setRecyclerView(it.data)
                }
            })

            errorMessage.observe(viewLifecycleOwner,Observer{
                it?.let {
                    binding.errorTextView.visibility = View.VISIBLE
                    binding.errorTextView.text = it
                }
            })
        }

    }

    private fun setRecyclerView(dataList : List<Data>) {

        binding.cryptoRecyclerView.visibility = View.VISIBLE
        binding.cryptoRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.cryptoRecyclerView.adapter = cryptoAdapter
        cryptoAdapter.refreshList(dataList)

    }


}