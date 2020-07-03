package com.app.shelter.detailedList.reducers

import com.app.shelter.core.model.Pet
import com.app.shelter.core.model.PetOwner
import com.app.shelter.core.model.PetType
import com.app.shelter.detailedList.model.DetailedScreenDestinations
import com.app.shelter.detailedList.model.DetailedScreenNews
import com.app.shelter.detailedList.model.DetailedScreenState
import com.app.shelter.petsList.model.ListFragmentNews
import com.app.shelter.petsList.model.ListFragmentState
import com.app.shelter.storage.DataStorage
import com.app.shelter.storage.room.entitys.OwnerEntity
import com.app.shelter.storage.room.entitys.PetEntity
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.CoroutineName
import java.lang.Exception
import javax.inject.Inject

class PetDetailsReducer @Inject constructor(
    private val dataStorage: DataStorage,
    private val scope: CoroutineScope
) : DetailedLogic {
    private val _screenState: PublishSubject<DetailedScreenState> = PublishSubject.create()
    override val screenState: Observable<DetailedScreenState>
        get() = _screenState

    private val _screenNews: PublishSubject<DetailedScreenNews> = PublishSubject.create()
    override val screenNews: Observable<DetailedScreenNews>
        get() = _screenNews

    private var state = DetailedScreenState()

    override fun loadPetData(petId: Int) {
        state = state.copy(isLoading = true)
        _screenState.onNext(state)

        scope.launch(Dispatchers.Default) {

            state = try {
                val petEntity: PetEntity = withContext(CoroutineName("Getting Pet Data")) {
                    dataStorage.getPetById(petId)
                }
                val petData = Pet(
                    petEntity.id ?: -1,
                    petEntity.name,
                    PetType.valueOf(petEntity.type),
                    petEntity.ownerId
                )

                val ownedEntity: OwnerEntity = withContext(CoroutineName("Getting Owner Data")) {
                    dataStorage.getOwnerById(petEntity.ownerId)
                }
                val ownerPetsEntities = withContext(CoroutineName("Getting Owner Pets")) {
                    dataStorage.getOwnerPetsList(petEntity.ownerId)
                }

                val ownerPets = ownerPetsEntities.map {
                    Pet(
                        it.id ?: -1,
                        it.name,
                        PetType.valueOf(it.type),
                        it.ownerId
                    )
                }

                val ownerData = PetOwner(
                    ownedEntity.id ?: -1,
                    ownedEntity.name,
                    ownedEntity.lastName,
                    ownerPets
                )
                DetailedScreenState(
                    isLoading = false,
                    petData = petData,
                    ownerData = ownerData
                )
            } catch (error: Exception) {
                _screenNews.onNext(
                    DetailedScreenNews(
                        errorMessage = error.message ?: "UnknownError"
                    )
                )
                DetailedScreenState(isLoading = false)
            }
            _screenState.onNext(state)
        }
    }

    override fun backButtonTap() {
        _screenNews.onNext(
            DetailedScreenNews(
                destination = DetailedScreenDestinations.PETS_LIST
            )
        )
    }

}
