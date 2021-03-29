package com.nikolai.mvpexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class MainFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.simpleMVP).setOnClickListener {
            view.findNavController().navigate(R.id.action_mainFragment_to_simpleMVPFragment)
        }

        view.findViewById<Button>(R.id.moxyMVP).setOnClickListener {
            view.findNavController().navigate(R.id.action_mainFragment_to_moxyFragment)
        }

        view.findViewById<Button>(R.id.simpleSuperViserMVP).setOnClickListener {
            view.findNavController().navigate(R.id.action_mainFragment_to_simpleMVPSuperviserFragment)
        }
    }
}