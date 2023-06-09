package com.example.feature_shop.dish

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.feature_shop.DISH_DIALOG_TAG
import com.example.feature_shop.DISH_KEY
import com.example.feature_shop.ShopComponent
import com.example.feature_shop.databinding.FragmentDishBinding
import com.example.feature_shop.databinding.TabCustomBinding
import com.google.android.material.tabs.TabLayoutMediator

class   DishFragment: Fragment(){

    private val viewModel: DishViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDishBinding.inflate(inflater)
        val adapter = DishPagerAdapter()
        val dialog = DishDialog()

        binding.dishPager.adapter = adapter

        TabLayoutMediator(binding.dishTabs, binding.dishPager){ tab, pos ->
            val tabBinding = TabCustomBinding.inflate(inflater)
            tab.customView = tabBinding.root

            with(viewModel) {
                tabBinding.root.setTextColor(ColorStateList(states, colors))
                tabBinding.root.text = tabNames[pos]
            }
        }.attach()

        with(ShopComponent.init(requireActivity())){
            inject(viewModel)
            inject(dialog)
        }

        viewModel.getDishes()

        viewModel.dishesData.observe(viewLifecycleOwner){
            DishMapper.mapByTegs(it, viewModel.tabNames).forEach{ dishesByTeg ->
                adapter.addItem(DishPagerItem(dishesByTeg))
            }
        }

        adapter.setOnDishClick{ _, item ->
            dialog.arguments = Bundle().apply { putSerializable(DISH_KEY, item) }
            dialog.show(childFragmentManager, DISH_DIALOG_TAG)
        }

        return binding.root
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }

}