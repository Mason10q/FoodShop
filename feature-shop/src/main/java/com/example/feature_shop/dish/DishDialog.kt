package com.example.feature_shop.dish

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.example.feature_shop.DISH_DIALOG_RESULT
import com.example.feature_shop.DISH_DIALOG_TAG
import com.example.feature_shop.DISH_KEY
import com.example.feature_shop.databinding.DialogDishBinding
import com.squareup.picasso.Picasso
import javax.inject.Inject

class DishDialog : DialogFragment() {

    @Inject
    lateinit var picasso: Picasso

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val binding = DialogDishBinding.inflate(layoutInflater)
        builder.setView(binding.root)
        val dish: com.example.core_entities.Dish = arguments.let { it?.getSerializable(DISH_KEY) as com.example.core_entities.Dish }

        with(binding) {
            picasso.load(dish.imageUrl).into(dialogDishImage)
            dialogDishName.text = dish.name
            dialogDishPrice.text = dish.price.toString()
            dialogDishWeight.text = dish.weight.toString()
            dialogDishDescription.text = dish.description

            addToBasketBtn.setOnClickListener { finish(true) }
            dialogDismissBtn.setOnClickListener{ finish(false) }
        }

        return builder.create()
    }


    private fun finish(result: Boolean){
        parentFragment?.setFragmentResult(
            DISH_DIALOG_TAG,
            bundleOf(DISH_DIALOG_RESULT to result)
        )

        dismiss()
    }

}