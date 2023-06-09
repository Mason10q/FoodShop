package com.example.feature_shop.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.feature_shop.ShopComponent
import com.example.feature_shop.databinding.FragmentCategoryBinding

class CategoryFragment: Fragment(){

    private val viewModel: CategoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCategoryBinding.inflate(layoutInflater)
        val adapter = CategoryAdapter()
        binding.categoryList.adapter = adapter

        with(ShopComponent.init(requireActivity())) {
            inject(viewModel)
            inject(adapter)
        }
        viewModel.getCategories()

        viewModel.error.observe(viewLifecycleOwner){
            Toast.makeText(requireActivity(), it, Toast.LENGTH_LONG).show()
        }

        viewModel.categoriesData.observe(viewLifecycleOwner){
            adapter.addItems(it)
        }

        return binding.root
    }


    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }

}