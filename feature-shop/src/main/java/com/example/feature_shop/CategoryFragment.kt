package com.example.feature_shop

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.feature_shop.databinding.FragmentCategoryBinding
import com.squareup.picasso.Picasso

class CategoryFragment: Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCategoryBinding.inflate(layoutInflater)
        val adapter = CategoryAdapter()
        binding.categoryList.adapter = adapter

        ShopComponent.init(requireActivity()).inject(adapter)


        return binding.root
    }

}