package com.nikolai.rxjavabyexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.fragment_main_selection.*

class MainSelectionFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imperativeButton.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_mainSelectionFragment_to_imperativeCodeFragment)
        }

        basicExampleButton.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_mainSelectionFragment_to_simpleSourceExampleFragment)
        }
    }
}