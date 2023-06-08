package com.example.core_android

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import androidx.cardview.widget.CardView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.lang.Exception

class CustomCardView: CardView, Target {

    constructor(context: Context) : super(context)
    constructor(context: Context, attributes: AttributeSet) : super(context, attributes)
    constructor(context: Context, attributes: AttributeSet, defStyleAttr: Int) : super(context, attributes, defStyleAttr)


    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
        background = BitmapDrawable(resources, bitmap)
    }

    override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
        Log.d("bitmapFailed", e?.message.toString())
    }

    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
}