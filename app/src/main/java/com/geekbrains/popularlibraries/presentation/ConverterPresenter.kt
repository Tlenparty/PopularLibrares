package com.geekbrains.popularlibraries.presentation

import android.net.Uri
import com.geekbrains.popularlibraries.framework.ui.view.converter_fragment.ConverterView
import com.geekbrains.popularlibraries.model.converter.Converter
import com.geekbrains.popularlibraries.scheduler.Schedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import moxy.MvpPresenter

class ConverterPresenter(
    private val converter: Converter,
    private val schedulers: Schedulers
): MvpPresenter<ConverterView>() {

    private val disposables = CompositeDisposable()

    fun convert(uri: Uri) {
        viewState.showContent(uri)
        viewState.showLoading()

        disposables +=
            converter
                .convert(uri)
                .observeOn(schedulers.main())
                .subscribeOn(schedulers.background())
                .subscribe(
                    viewState::showContent,
                    viewState::showError
                )
    }

    fun cancel() {
        viewState.showContent(null)
        disposables.clear()
    }

    override fun onDestroy() = disposables.clear()

}