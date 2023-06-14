package com.example.feature_shop.dish

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.feature_shop.CATEGORY_NAME_KEY
import com.example.feature_shop.DISH_DIALOG_TAG
import com.example.feature_shop.DISH_KEY
import com.example.feature_shop.ShopComponent
import com.example.feature_shop.databinding.CustomTabBinding
import com.example.feature_shop.databinding.FragmentDishBinding
import com.google.android.material.tabs.TabLayoutMediator
import javax.inject.Inject

class DishFragment : Fragment() {

    private val binding by lazy { FragmentDishBinding.inflate(layoutInflater) }

    private val viewModel: DishViewModel by viewModels()
    @Inject lateinit var adapter: DishPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        with(ShopComponent.init(requireActivity())) {
            inject(viewModel)
            inject(this@DishFragment)
        }

        prepareAdapter()
        prepareToolbar()
        viewModel.getDishes()

        viewModel.dishesData.observe(viewLifecycleOwner) {
            DishMapper.mapByTegs(it, viewModel.tabNames).forEach { dishesByTeg ->
                adapter.addItem(DishPagerItem(dishesByTeg))
            }
        }

        return binding.root
    }


    private fun prepareAdapter(){
        binding.dishPager.adapter = adapter

        adapter.setOnDishClick { _, item ->
            val dialog = DishDialog()
            dialog.arguments = Bundle().apply { putSerializable(DISH_KEY, item) }
            dialog.show(childFragmentManager, DISH_DIALOG_TAG)
        }

        attacheMediator()
    }

    private fun attacheMediator(){

        TabLayoutMediator(binding.dishTabs, binding.dishPager) { tab, pos ->
            val tabBinding = CustomTabBinding.inflate(layoutInflater).customTab
            tabBinding.text = viewModel.tabNames[pos]
            tab.customView = tabBinding
        }.attach()
    }

    private fun prepareToolbar(){
        binding.toolbar.backBtn.setOnClickListener{ findNavController().navigateUp() }
        binding.toolbar.categoryName.text = arguments?.getString(CATEGORY_NAME_KEY)
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }

}