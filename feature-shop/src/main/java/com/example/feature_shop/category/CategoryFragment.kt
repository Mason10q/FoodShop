package com.example.feature_shop.category

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core_android.R
import com.example.core_android.UserData
import com.example.core_android.UserDataProvider
import com.example.core_android.createRecyclerView
import com.example.core_android.overplaceEmptyList
import com.example.feature_shop.CATEGORY_NAME_KEY
import com.example.feature_shop.ShopComponent
import com.example.feature_shop.databinding.FragmentCategoryBinding
import javax.inject.Inject

class CategoryFragment : Fragment() {

    private val binding by lazy { FragmentCategoryBinding.inflate(layoutInflater) }
    private val viewModel: CategoryViewModel by viewModels()
    @Inject
    lateinit var adapter: CategoryAdapter

    private var isProblemLayout = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        with(ShopComponent.init(requireActivity())) {
            inject(viewModel)
            inject(this@CategoryFragment)
        }

        val userDataProvider = UserDataProvider(requireContext())

        prepareAdapter()
        prepareToolbar(userDataProvider.getUserData())
        createObservers()
        viewModel.getCategories()

        return binding.root
    }

    private fun prepareAdapter() {
        binding.categoryList.adapter = adapter

        adapter.setOnCategoryClickListener { _, item ->
            findNavController().navigate(
                com.example.core_navigation.R.id.dishFragment,
                Bundle().apply {
                    putString(
                        CATEGORY_NAME_KEY, item.name
                    )
                })
        }
    }

    private fun prepareToolbar(userData: UserData){
        binding.toolbar.city.text = userData.city
        binding.toolbar.date.text = userData.date
    }

    private fun createObservers() {
        viewModel.error.observe(viewLifecycleOwner) {
            setProblemView()
        }

        viewModel.categoriesData.observe(viewLifecycleOwner) {
            if (isProblemLayout) {
                setRecycler()
            }
            adapter.addItems(it)
        }
    }

    private fun setProblemView() {
        isProblemLayout = true
        binding.container.removeAllViews()
        binding.container.addView(overplaceEmptyList(
            binding.container,
            R.drawable.ic_internet_lost,
            com.example.feature_shop.R.string.no_internet,
            com.example.feature_shop.R.string.to_settings
        ) { startActivity(Intent(Settings.ACTION_WIRELESS_SETTINGS)) })
    }

    private fun setRecycler() {
        isProblemLayout = false
        with(binding.container) {
            removeAllViews()
            addView(
                createRecyclerView(
                    requireContext(),
                    adapter,
                    LinearLayoutManager(requireContext())
                )
            )
        }
    }


    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }

}