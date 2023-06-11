package com.example.feature_basket

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.feature_basket.databinding.FragmentBasketBinding
import javax.inject.Inject

class BasketFragment: Fragment(){

    private val viewModel: BasketViewModel by viewModels()
    @Inject lateinit var adapter: BasketAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("basket", "onCreateView")
        val binding = FragmentBasketBinding.inflate(inflater)

        with(BasketComponent.init(requireActivity())){
            inject(viewModel)
            inject(this@BasketFragment)
        }

        viewModel.getAllBasket()

        binding.basketRecycler.adapter = adapter

        viewModel.basketData.observe(viewLifecycleOwner){
            Log.d("basket", "observer")
            adapter.addItems(it)
        }

        binding.payBtn.setOnClickListener { viewModel.getAllBasket() }

        return binding.root
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }

}