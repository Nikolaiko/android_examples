package com.app.shelter.petsList.views

import android.animation.LayoutTransition
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.shelter.R
import com.app.shelter.ShelterApp
import com.app.shelter.petsList.di.PETS_LIST_SCOPE_NAME
import com.app.shelter.petsList.di.PetsListModule
import com.app.shelter.petsList.model.PetShortData
import com.app.shelter.petsList.presenters.PetsListPresentation
import com.app.shelter.petsList.views.elements.PetsListAdapter
import com.app.shelter.storage.di.DataStorageModule
import kotlinx.android.synthetic.main.fragment_pets_list.*
import kotlinx.coroutines.GlobalScope
import toothpick.Scope
import toothpick.Toothpick
import toothpick.ktp.KTP
import java.io.Serializable
import javax.inject.Inject

class PetsListFragment : Fragment(), PetsListView {

    @Inject
    lateinit var petsPresenter: PetsListPresentation
    private var scope: Scope? = null
    private var adapter: PetsListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pets_list, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val convertedApp = activity?.application as ShelterApp
        scope = convertedApp.getRootScope()
            scope
                ?.openSubScope(this)
                ?.installModules(PetsListModule())
                ?.installModules(DataStorageModule(lifecycleScope))
                ?.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = PetsListAdapter()
        petsListView.layoutManager = LinearLayoutManager(context)
        petsListView.setAdapter(adapter)

        petsPresenter.bind(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        petsPresenter.unbind()
    }

    override fun onDestroy() {
        super.onDestroy()
        KTP.closeScope(this)
    }

    override fun setLoadingIndicator(isLoading: Boolean) {
        if (isLoading) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.INVISIBLE
        }
    }

    override fun updatePetsList(pets: List<PetShortData>) {
        activity?.runOnUiThread {
            adapter?.updateList(pets)
            adapter?.notifyDataSetChanged()
        }
    }
}