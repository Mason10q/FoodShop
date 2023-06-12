package com.example.feature_shop.category

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.feature_shop.ShopComponent
import com.example.feature_shop.databinding.FragmentCategoryBinding
import javax.inject.Inject

class CategoryFragment: Fragment(){

    private val binding by lazy { FragmentCategoryBinding.inflate(layoutInflater) }
    private val viewModel: CategoryViewModel by viewModels()
    @Inject lateinit var adapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        with(ShopComponent.init(requireActivity())) {
            inject(viewModel)
            inject(this@CategoryFragment)
        }

        prepareAdapter()
        createObservers()
        viewModel.getCategories()

        return binding.root
    }

    private fun prepareAdapter(){
        binding.categoryList.adapter = adapter

        adapter.setOnCategoryClickListener { _, _ ->
            findNavController().navigate(com.example.core_navigation.R.id.dishFragment)
        }
    }

    private fun createObservers(){
        viewModel.error.observe(viewLifecycleOwner){
            Toast.makeText(requireActivity(), it, Toast.LENGTH_LONG).show()
        }

        viewModel.categoriesData.observe(viewLifecycleOwner){
            adapter.addItems(it)
        }
    }


    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }

}