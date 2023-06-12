package com.example.feature_basket

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.core_android.createRecyclerView
import com.example.core_android.databinding.ProblemLayoutBinding
import com.example.core_android.overplaceEmptyList
import com.example.feature_basket.databinding.FragmentBasketBinding
import javax.inject.Inject

class BasketFragment : Fragment() {

    private val viewModel: BasketViewModel by viewModels()
    @Inject
    lateinit var adapter: BasketAdapter

    private var isProblemLayout = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentBasketBinding.inflate(inflater)

        with(BasketComponent.init(requireActivity())) {
            inject(viewModel)
            inject(this@BasketFragment)
        }

        adapter.setDishAmountCallback(object : DishAmountController {
            override fun increase(id: Int) {
                viewModel.increaseAmount(id)
            }

            override fun decrease(id: Int) {
                viewModel.decreaseAmount(id)
            }

            override fun delete(id: Int) {
                viewModel.deleteFromTable(id)
            }
        })

        viewModel.getAllBasket()

        binding.basketRecycler.adapter = adapter
        binding.payBtn.text = resources.getString(R.string.pay, 1)

        viewModel.basketData.observe(viewLifecycleOwner) {
            adapter.addItems(it)
        }

        viewModel.amountChangedData.observe(viewLifecycleOwner) {
            setView(binding)
            binding.payBtn.text = resources.getString(R.string.pay, adapter.countFullPrice())
        }

        return binding.root
    }


    private fun setView(binding: FragmentBasketBinding) {

        if (adapter.itemCount != 0 && !isProblemLayout) {
            return
        }

        if (adapter.itemCount == 0) {
            isProblemLayout = true
            binding.container.removeAllViews()
            binding.container.addView(overplaceEmptyList(
                binding.container,
                com.example.core_android.R.drawable.bag,
                R.string.empry_basket,
                R.string.to_shop
            ) { findNavController().navigate(com.example.core_navigation.R.id.nav_shop) })

            binding.payBtn.isVisible = false
        } else {
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
            binding.payBtn.isVisible = true
        }
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }

}