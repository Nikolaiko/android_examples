package com.nikolai.mvpexample.simpleMVP

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.nikolai.mvpexample.R

class SimpleMVPFragment : Fragment() {
    private val presenter: SimpleMVPPresenter = SimpleMVPPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_simple_mvp, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.backFromSimpleMVP).setOnClickListener {
            view.findNavController().popBackStack()
        }

        view.findViewById<Button>(R.id.startAction).setOnClickListener {
            presenter.startAction()
        }

        presenter.attach(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

    fun startAction() {
        view?.findViewById<Button>(R.id.startAction)?.isEnabled = false
        view?.findViewById<Button>(R.id.backFromSimpleMVP)?.isEnabled = false
        view?.findViewById<ProgressBar>(R.id.progressBar)?.visibility = View.VISIBLE
    }

    fun stopAction() {
        view?.findViewById<Button>(R.id.startAction)?.isEnabled = true
        view?.findViewById<Button>(R.id.backFromSimpleMVP)?.isEnabled = true
        view?.findViewById<ProgressBar>(R.id.progressBar)?.visibility = View.INVISIBLE
    }
}