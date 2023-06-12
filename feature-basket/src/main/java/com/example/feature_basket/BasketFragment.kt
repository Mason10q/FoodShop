package com.example.feature_basket

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.core_android.databinding.ProblemLayoutBinding
import com.example.feature_basket.databinding.FragmentBasketBinding
import javax.inject.Inject

class BasketFragment: Fragment(){

    private val viewModel: BasketViewModel by viewModels()
    @Inject lateinit var adapter: BasketAdapter

    private var isProblemLayout = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentBasketBinding.inflate(inflater)

        with(BasketComponent.init(requireActivity())){
            inject(viewModel)
            inject(this@BasketFragment)
        }

        adapter.setDishAmountCallback(object: DishAmountController{
            override fun increase(id: Int) { viewModel.increaseAmount(id) }
            override fun decrease(id: Int) { viewModel.decreaseAmount(id) }
            override fun delete(id: Int) { viewModel.deleteFromTable(id) }
        })

        viewModel.getAllBasket()

        binding.basketRecycler.adapter = adapter
        binding.payBtn.text = resources.getString(R.string.pay, 1)

        viewModel.basketData.observe(viewLifecycleOwner){
            adapter.addItems(it)
        }

        viewModel.amountChangedData.observe(viewLifecycleOwner){
            setView(binding)
            binding.payBtn.text = resources.getString(R.string.pay, adapter.countFullPrice())
        }

        return binding.root
    }


    private fun setView(binding: FragmentBasketBinding){

        if(adapter.itemCount != 0 && !isProblemLayout){
            return
        }

        if(adapter.itemCount == 0) {
            isProblemLayout = true
            binding.container.removeAllViews()
            binding.container.addView(
                ProblemLayoutBinding.inflate(layoutInflater).apply {
                    notifyImage.setImageResource(com.example.core_android.R.drawable.bag)
                    notifyText.text = "Корзина пуста"
                    notifyButton.text = "В магазин"
                    notifyButton.setOnClickListener { findNavController().navigate(com.example.core_navigation.R.id.nav_shop) }
                }.root
            )
        }
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }

}