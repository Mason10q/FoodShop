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
            binding.payBtn.text = resources.getString(R.string.pay, adapter.countFullPrice())
        }

        return binding.root
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }

}