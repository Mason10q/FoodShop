package com.example.core_android

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.CompletableSource
import io.reactivex.rxjava3.core.CompletableTransformer
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.MaybeSource
import io.reactivex.rxjava3.core.MaybeTransformer
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleSource
import io.reactivex.rxjava3.core.SingleTransformer
import io.reactivex.rxjava3.schedulers.Schedulers

sealed class RxJavaUtil{

    class SingleTransUtil<T : Any> : SingleTransformer<T, T>, RxJavaUtil() {

        override fun apply(upstream: Single<T>): SingleSource<T> {
            return upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    class MaybeTransUtil<T: Any> : MaybeTransformer<T, T>, RxJavaUtil() {
        override fun apply(upstream: Maybe<T>): MaybeSource<T> {
            return upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    object CompletableTransUtil : CompletableTransformer, RxJavaUtil() {
        override fun apply(upstream: Completable): CompletableSource {
            return upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }

    }

}
