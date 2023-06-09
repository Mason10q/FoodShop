package com.example.feature_shop.dish

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.feature_shop.ShopComponent
import com.example.feature_shop.ShopViewModel
import com.example.feature_shop.databinding.FragmentDishBinding
import com.google.android.material.tabs.TabLayoutMediator

class DishFragment: Fragment(){

    private val viewModel: ShopViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDishBinding.inflate(inflater)
        val tabNames = listOf("Все меню", "Салаты", "С рисом", "С рыбой")
        val adapter = DishPagerAdapter()

        binding.dishPager.adapter = adapter

        TabLayoutMediator(binding.dishTabs, binding.dishPager){ tab, pos ->
            tab.text = tabNames[pos]
        }.attach()

        with(ShopComponent.init(requireActivity())){
            inject(viewModel)
        }

        viewModel.getDishes()

        viewModel.dishesData.observe(viewLifecycleOwner){
            DishMapper.mapByTegs(it, tabNames).forEach{ dishesByTeg ->
                adapter.addItem(DishPagerItem(dishesByTeg))
            }
        }

        return binding.root
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }

}