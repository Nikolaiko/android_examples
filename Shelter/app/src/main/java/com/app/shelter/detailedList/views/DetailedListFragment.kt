package com.app.shelter.detailedList.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.app.shelter.R
import com.app.shelter.ShelterApp
import com.app.shelter.petsList.di.PetsListModule
import com.app.shelter.storage.di.DataStorageModule
import toothpick.Scope
import toothpick.config.Module
import toothpick.ktp.KTP

class DetailedListFragment : Fragment(), DetailedView {
    private var scope: Scope? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detailed_data, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val convertedApp = activity?.application as ShelterApp
        scope = convertedApp.getRootScope()
        scope
            ?.openSubScope(this)
            ?.installModules()
            ?.installModules(DataStorageModule(lifecycleScope))
            ?.inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        KTP.closeScope(this)
    }
}