package com.example.fagments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.R
import kotlinx.android.synthetic.main.fragment_main_list.*

class MainListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        readExamplesButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainListFragment_to_basicOperationsFragment)
        }

        modificationsButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainListFragment_to_modificationsFragment)
        }

        skinTestButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainListFragment_to_skinTestFragment)
        }
    }
}