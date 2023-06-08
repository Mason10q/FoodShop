package com.example.core_android

import android.content.Context
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides

@Module
class AndroidModule(private val context: Context) {

    @Provides
    fun providePicasso(): Picasso = Picasso.Builder(context).build()

}