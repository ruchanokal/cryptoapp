package com.ruchanokal.cryptoapp.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.ruchanokal.cryptoapp.R
import com.ruchanokal.cryptoapp.databinding.FragmentDetailBinding
import com.ruchanokal.cryptoapp.ui.BaseFragment
import com.ruchanokal.cryptoapp.ui.main.MainFragment
import com.ruchanokal.cryptoapp.ui.main.MainFragmentDirections
import com.ruchanokal.cryptoapp.util.Constants.API_KEY
import com.ruchanokal.cryptoapp.util.downloadImage
import com.ruchanokal.cryptoapp.util.placeholderProgressBar
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class DetailFragment
    : BaseFragment<FragmentDetailBinding, DetailViewModel>(FragmentDetailBinding::inflate) {



    override val viewModel by viewModels<DetailViewModel>()
    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateFinished() {

        viewModel.getDetailData(API_KEY,args.symbol)

    }

    override fun observeEvents() {

        with(viewModel){

            detailInfo.observe(viewLifecycleOwner, Observer {

                it?.let {

                    binding.contentViewLayout.isVisible = true
                    binding.detailPhoto.downloadImage(it.id.toString(), placeholderProgressBar(requireContext()))
                    binding.cryptoNameText.text = it.name
                    binding.cryptoDescText.text = it.description

                }

            })

            error.observe(viewLifecycleOwner, Observer {

                it?.let {
                    binding.errorTextView.isVisible = true
                    binding.errorTextView.text = it
                }

            })

            isLoading.observe(viewLifecycleOwner, Observer {
                binding.progressBar.isVisible = it
            })



        }


    }


}