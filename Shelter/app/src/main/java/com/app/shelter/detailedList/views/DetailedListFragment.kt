package com.app.shelter.detailedList.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.app.shelter.R
import com.app.shelter.ShelterApp
import com.app.shelter.core.const.PET_ID_PARAMETER_NAME
import com.app.shelter.core.model.Pet
import com.app.shelter.core.model.PetOwner
import com.app.shelter.detailedList.di.DetailedListModule
import com.app.shelter.detailedList.model.DetailedScreenDestinations
import com.app.shelter.detailedList.presenters.DetailedPresentation
import com.app.shelter.petsList.di.PetsListModule
import com.app.shelter.storage.di.DataStorageModule
import kotlinx.android.synthetic.main.fragment_detailed_data.*
import toothpick.Scope
import toothpick.config.Module
import toothpick.ktp.KTP
import javax.inject.Inject

class DetailedListFragment : Fragment(), DetailedView {
    @Inject
    lateinit var presenter: DetailedPresentation

    private var id: Int? = null
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
            ?.installModules(DetailedListModule(lifecycleScope))
            ?.installModules(DataStorageModule())
            ?.inject(this)

        id = arguments?.getInt(PET_ID_PARAMETER_NAME)
    }

    override fun onDestroy() {
        super.onDestroy()
        KTP.closeScope(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backButton.setOnClickListener {
            presenter.backButtonTap()
        }

        presenter.bind(this)
        if (id != null) {
            presenter.loadPetData(id!!)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unbind()
    }

    override fun setLoadingIndicator(isLoading: Boolean) {
        if (isLoading) {
            progressBar2.visibility = View.VISIBLE
        } else {
            progressBar2.visibility = View.GONE
        }
    }

    override fun navigateTo(destination: DetailedScreenDestinations, data: Any?) {
        when(destination) {
            DetailedScreenDestinations.PETS_LIST -> NavHostFragment.findNavController(this).popBackStack()
            else -> {
                //Timber
            }
        }
    }

    override fun displayErrorMessage(message: String) {
        //Timber
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun setPetAndOwnerData(pet: Pet, owner: PetOwner) {
        petNameText.text = pet.name
        petTypeText.text = pet.type.name

        ownerNameText.text = owner.name
        petsCountText.text = owner.pets.size.toString()
    }
}