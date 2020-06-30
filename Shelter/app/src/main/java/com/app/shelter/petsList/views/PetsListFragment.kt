package com.app.shelter.petsList.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.shelter.R
import com.app.shelter.petsList.di.PETS_LIST_SCOPE_NAME
import com.app.shelter.petsList.di.PetsListModule
import com.app.shelter.petsList.presenters.PetsListPresentation
import com.app.shelter.storage.di.DataStorageModule
import toothpick.Toothpick
import javax.inject.Inject

class PetsListFragment : Fragment() {

    @Inject
    lateinit var petsPresenter: PetsListPresentation

    private val scope = Toothpick.openScope(PETS_LIST_SCOPE_NAME)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pets_list, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        scope
            .installModules(PetsListModule())
            .installModules(DataStorageModule())
            .inject(this)
    }
}