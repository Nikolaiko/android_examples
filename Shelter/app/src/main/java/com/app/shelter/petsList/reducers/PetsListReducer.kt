package com.app.shelter.petsList.reducers

import com.app.shelter.core.model.PetType
import com.app.shelter.petsList.model.ListFragmentDestinations
import com.app.shelter.petsList.model.ListFragmentNews
import com.app.shelter.petsList.model.ListFragmentState
import com.app.shelter.petsList.model.PetShortData
import com.app.shelter.storage.DataStorage
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PetsListReducer @Inject constructor(
    private val dataStorage: DataStorage,
    private val scope: CoroutineScope
): PetsListLogic {
    private var state = ListFragmentState()

    private val _screenState: PublishSubject<ListFragmentState> = PublishSubject.create()
    override val screenState: Observable<ListFragmentState>
        get() = _screenState

    private val _screenNews: PublishSubject<ListFragmentNews> = PublishSubject.create()
    override val screenNews: Observable<ListFragmentNews>
        get() = _screenNews

    init {
        scope.launch(Dispatchers.Default) {
            dataStorage.prePopulateIfNeeded()
        }
    }

    override fun refreshData() {
        scope.launch(Dispatchers.Default) {
            state = state.copy(isLoading = true)
            _screenState.onNext(state)

            val updatedPetData = try {
                val petsEntityShortData =
                    withContext(Dispatchers.Default) { dataStorage.getShortDataList() }
                petsEntityShortData.map {
                    PetShortData(it.id, it.name, PetType.valueOf(it.type))
                }
            } catch(error: Exception) {
                _screenNews.onNext(ListFragmentNews(errorMessage = error.message ?: "Unknown message"))
                emptyList<PetShortData>()
            }
            state = ListFragmentState(false, updatedPetData)
            _screenState.onNext(state)
        }
    }

    override fun petRowSelected(petId: Int) {
        _screenNews.onNext(
            ListFragmentNews(
                destination = ListFragmentDestinations.DETAILED_LIST,
                data = petId
            )
        )
    }
}
