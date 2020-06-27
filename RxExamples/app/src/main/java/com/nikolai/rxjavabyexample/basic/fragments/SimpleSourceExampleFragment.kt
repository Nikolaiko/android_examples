package com.nikolai.rxjavabyexample.basic.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nikolai.rxjavabyexample.R
import com.nikolai.rxjavabyexample.basic.presenters.SourcePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_reactive_source.*

class SimpleSourceExampleFragment : Fragment() {
    private val presenter: SourcePresenter = SourcePresenter()
    private val disposeBag: CompositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reactive_source, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.requestResult
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                resultText.text = "Error : ${it.message}"
            }
            .subscribe {
                resultText.text = it
            }
            .addTo(disposeBag)

        performRequestButton.setOnClickListener {
            presenter.successfulRequest()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposeBag.clear()
    }
}