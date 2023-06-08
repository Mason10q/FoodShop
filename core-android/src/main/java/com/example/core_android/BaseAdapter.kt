package com.example.abstracttesting.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.core_android.AdapterCallback

abstract class BaseAdapter<DATA : Any, B : ViewBinding> (
    private val inflater: (LayoutInflater, ViewGroup, Boolean) -> B
) : RecyclerView.Adapter<BaseAdapter.ViewHolder<DATA, B>>(), AdapterCallback<DATA, B>{

    private val items = mutableListOf<DATA>()


    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<DATA>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun addItem(item: DATA) {
        this.items.add(item)
        notifyItemInserted(items.size-1)
    }

    fun addItems(items: List<DATA>) {
        this.items.addAll(items)
        notifyDataSetChanged()//TODO: Выбрать более оптимизированный метод
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearItems() {
        this.items.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int) : ViewHolder<DATA, B> = ViewHolder(
        inflater(LayoutInflater.from(parent.context), parent, false), this
    )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder<DATA, B>, pos: Int) = holder.bind(items[pos], pos)

    class ViewHolder<DATA : Any, B : ViewBinding>(
        private val binding: B,
        private val callbacks: AdapterCallback<DATA, B>
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DATA, position: Int) {
            callbacks.bindViews(binding, item, position)
            binding.root.setOnClickListener { callbacks.onViewClicked(binding.root, item) }
        }

    }
}