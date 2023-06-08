package com.example.core_android

import android.view.View

interface AdapterCallback<DATA, B> {

    fun bindViews(binding: B, item: DATA, position: Int)
    fun onViewClicked(view: View, item: DATA)

}