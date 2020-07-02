package com.app.shelter.petsList.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.shelter.R
import com.app.shelter.ShelterApp
import com.app.shelter.core.const.PET_ID_PARAMETER_NAME
import com.app.shelter.petsList.di.PetsListModule
import com.app.shelter.petsList.model.ListFragmentDestinations
import com.app.shelter.petsList.model.PetShortData
import com.app.shelter.petsList.presenters.PetsListPresentation
import com.app.shelter.petsList.views.elements.PetsListAdapter
import com.app.shelter.storage.di.DataStorageModule
import kotlinx.android.synthetic.main.fragment_pets_list.*
import toothpick.Scope
import toothpick.ktp.KTP
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
                ?.installModules(PetsListModule(lifecycleScope))
                ?.installModules(DataStorageModule())
                ?.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = PetsListAdapter {
            petsPresenter.petRowClicked(it)
        }
        petsListView.layoutManager = LinearLayoutManager(context)
        petsListView.adapter = adapter

        refreshButton.setOnClickListener {
            petsPresenter.refresh()
        }

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
        refreshButton.isEnabled = !isLoading
    }

    override fun updatePetsList(pets: List<PetShortData>) {
        activity?.runOnUiThread {
            adapter?.updateList(pets)
            adapter?.notifyDataSetChanged()
        }
    }

    override fun navigateTo(destination: ListFragmentDestinations, data: Any?) {
        when(destination) {
            ListFragmentDestinations.DETAILED_LIST -> {
                val paramsBundle = Bundle()
                if (data != null) {
                    paramsBundle.putInt(PET_ID_PARAMETER_NAME, data as Int)
                }

                NavHostFragment
                    .findNavController(this)
                    .navigate(R.id.action_petsListFragment_to_detailedListFragment, paramsBundle)
            }
            else -> {
                //Timber
            }
        }
    }

    override fun displayErrorMessage(message: String) {
        //Timber
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}