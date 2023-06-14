package com.example.feature_shop.dish

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.feature_shop.DISH_KEY
import com.example.feature_shop.R
import com.example.feature_shop.ShopComponent
import com.example.feature_shop.databinding.DialogDishBinding
import com.squareup.picasso.Picasso
import javax.inject.Inject

class DishDialog : DialogFragment() {

    @Inject
    lateinit var picasso: Picasso
    private val viewModel: DialogViewModel by viewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        with(ShopComponent.init(requireActivity())) {
            inject(viewModel)
            inject(this@DishDialog)
        }

        val builder = AlertDialog.Builder(requireActivity())
        val binding = DialogDishBinding.inflate(layoutInflater)
        builder.setView(binding.root)

        viewModel.checkData.observe(this){inBasket ->
            with(binding.addToBasketBtn) {
                isEnabled = !inBasket
                if (inBasket)
                    text = "Уже в корзине"
            }
        }

        val dish = arguments.let { it?.getSerializable(DISH_KEY) as Dish }
        viewModel.checkIfInBasket(dish.name)

        with(binding) {
            if (dish.imageUrl.isNotEmpty()) {
                picasso.load(dish.imageUrl).into(dialogDishImage)
            }
            dialogDishName.text = dish.name
            dialogDishPrice.text = resources.getString(R.string.price, dish.price)
            dialogDishWeight.text = resources.getString(R.string.weight, dish.weight)
            dialogDishDescription.text = dish.description

            addToBasketBtn.setOnClickListener {
                viewModel.addToBasket(dish)
                dismiss()
            }
            dialogDismissBtn.setOnClickListener { dismiss() }
        }

        return builder.create().apply { window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) }
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }

}