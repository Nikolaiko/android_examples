package com.nikolai.rxjavabyexample.basic.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nikolai.rxjavabyexample.R
import com.nikolai.rxjavabyexample.basic.network.NetworkListener
import com.nikolai.rxjavabyexample.basic.network.NetworkRequest
import kotlinx.android.synthetic.main.fragment_imperative_code.*

class ImperativeCodeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_imperative_code, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        performAsyncButton.setOnClickListener {
            val request = NetworkRequest()
            request.performRequest(object : NetworkListener {
                override fun onSuccess(result: String) {
                    if (activity?.isDestroyed == false) {
                        activity?.runOnUiThread {
                            resultText.text = result
                        }
                    }
                }

                override fun onFail(message: String) {
                    if (activity?.isDestroyed == false) {
                        activity?.runOnUiThread {
                            resultText.text = message
                        }
                    }
                }
            })
        }
    }
}