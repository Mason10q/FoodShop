package com.example.feature_basket

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.feature_basket.databinding.FragmentBasketBinding

class BasketFragment: Fragment(){

    private val viewModel: BasketViewModel by viewModels()
    private val adapter = BasketAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentBasketBinding.inflate(inflater)
        binding.basketRecycler.adapter = adapter

        with(BasketComponent.init(requireActivity())){
            inject(adapter)
            inject(viewModel)
        }

        viewModel.getAllBasket()

        viewModel.basketData.observe(viewLifecycleOwner){
            adapter.addItems(it)
        }

        return binding.root
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }

}