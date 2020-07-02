package com.app.shelter.detailedList.presenters

import com.app.shelter.detailedList.model.DetailedScreenDestinations
import com.app.shelter.detailedList.model.DetailedScreenNews
import com.app.shelter.detailedList.model.DetailedScreenState
import com.app.shelter.detailedList.reducers.DetailedLogic
import com.app.shelter.detailedList.views.DetailedView
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class PetDetailsPresenter @Inject constructor(
    private val reducer: DetailedLogic
) : DetailedPresentation {

    private var view: DetailedView? = null
    private val disposeBag = CompositeDisposable()

    override fun bind(presenterView: DetailedView) {
        view = presenterView

        reducer.screenState
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                parseState(it)
            }.addTo(disposeBag)

        reducer.screenNews
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                parseNews(it)
            }.addTo(disposeBag)
    }

    override fun unbind() {
        view = null
        disposeBag.clear()
    }

    override fun loadPetData(id: Int) {
        reducer.loadPetData(id)
    }

    override fun backButtonTap() {
        reducer.backButtonTap()
    }


    private fun parseState(state: DetailedScreenState) {
        view?.setLoadingIndicator(state.isLoading)
        if (state.petData != null && state.ownerData != null) {
            view?.setPetAndOwnerData(state.petData, state.ownerData)
        }
    }

    private fun parseNews(news: DetailedScreenNews) {
        if (news.destination != DetailedScreenDestinations.NOT_SET) {
            view?.navigateTo(news.destination, news.data)
        }

        if (news.errorMessage.isNotEmpty()) {
            view?.displayErrorMessage(news.errorMessage)
        }
    }
}