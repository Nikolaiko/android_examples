package com.app.shelter.petsList.presenters

import com.app.shelter.petsList.model.ListFragmentState
import com.app.shelter.petsList.reducers.PetsListLogic
import com.app.shelter.petsList.views.PetsListView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class PetsListPresenter @Inject constructor(
    private val reducer: PetsListLogic
) : PetsListPresentation {

    private var view: PetsListView? = null
    private val disposeBag = CompositeDisposable()

    override fun bind(presenterView: PetsListView) {
        view = presenterView

        reducer.screenState
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                parseState(it)
            }.addTo(disposeBag)

        reducer.refreshData()
    }

    override fun unbind() {
        view = null
        disposeBag.clear()
    }

    private fun parseState(newState: ListFragmentState) {
        println("parse")
        view?.setLoadingIndicator(newState.isLoading)
        view?.updatePetsList(newState.list)
    }
}