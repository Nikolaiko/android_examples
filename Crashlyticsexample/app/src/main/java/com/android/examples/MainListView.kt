package com.android.examples

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.crashlytics.FirebaseCrashlytics
import kotlinx.android.synthetic.main.fragment_main_list.*
import java.lang.Exception
import java.lang.RuntimeException

class MainListView : Fragment() {
    companion object {
        private const val CUSTOM_KEY: String = "bool_key"
        private const val CUSTOM_MESSAGE: String = "Starting main activity"
        private const val CUSTOM_ID: String = "3456"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        FirebaseCrashlytics.getInstance().setUserId(CUSTOM_ID)
        FirebaseCrashlytics.getInstance().log(CUSTOM_MESSAGE)
        return inflater.inflate(R.layout.fragment_main_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handledExceptionButton.setOnClickListener {
            activity?.runOnUiThread {
                try {
                    throw RuntimeException(resources.getString(R.string.simple_handled_exception_text))
                } catch (exception: RuntimeException) {
                    FirebaseCrashlytics.getInstance().recordException(exception)
                }
            }
        }

        simpleExceptionButton.setOnClickListener {
            activity?.runOnUiThread {
                FirebaseCrashlytics.getInstance().setCustomKey(CUSTOM_KEY, true)
                throw RuntimeException(resources.getString(R.string.simple_exception_text))
            }
        }
    }
}