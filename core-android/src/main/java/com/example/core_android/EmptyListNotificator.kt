package com.example.core_android

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.example.core_android.databinding.ProblemLayoutBinding

fun<T: RecyclerView.ViewHolder> createRecyclerView(
    context: Context,
    adapter: RecyclerView.Adapter<T>,
    manager: RecyclerView.LayoutManager
) = RecyclerView(context).apply {
    this.adapter = adapter
    this.layoutManager = manager
}

fun overplaceEmptyList(
    container: ViewGroup,
    @DrawableRes notifyImageId: Int,
    @StringRes notifyTextId: Int,
    @StringRes notifyButtonTextId: Int,
    listener: (view: View) -> Unit
): View = with(
    ProblemLayoutBinding.inflate(LayoutInflater.from(container.context), container, false)
){
    val context = container.context
    this.notifyImage.setImageDrawable(AppCompatResources.getDrawable(context, notifyImageId))
    notifyText.text = context.getText(notifyTextId)
    notifyButton.setOnClickListener{ listener(it) }
    notifyButton.text = container.context.getString(notifyButtonTextId)


    return this.root
}