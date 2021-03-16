package com.nikolai.mvpexample.moxyMVP

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.nikolai.mvpexample.R
import com.nikolai.mvpexample.simpleMVP.SimpleMVPPresenter
import moxy.MvpFragment
import moxy.presenter.InjectPresenter

class MoxyFragment : MvpFragment(), MyMoxyView {

    @InjectPresenter
    lateinit var presenter: MoxyPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_moxy_mvp, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.backFromMoxy).setOnClickListener {
            view.findNavController().popBackStack()
        }

        view.findViewById<Button>(R.id.startActionMoxy).setOnClickListener {
            presenter.startAction()
        }
    }

    override fun startAction() {
        view?.findViewById<Button>(R.id.startActionMoxy)?.isEnabled = false
        view?.findViewById<Button>(R.id.backFromMoxy)?.isEnabled = false
        view?.findViewById<ProgressBar>(R.id.progressBarMoxy)?.visibility = View.VISIBLE
    }

    override fun stopAction() {
        view?.findViewById<Button>(R.id.startActionMoxy)?.isEnabled = true
        view?.findViewById<Button>(R.id.backFromMoxy)?.isEnabled = true
        view?.findViewById<ProgressBar>(R.id.progressBarMoxy)?.visibility = View.INVISIBLE
    }
}