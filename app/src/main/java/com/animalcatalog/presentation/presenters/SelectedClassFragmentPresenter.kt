package com.animalcatalog.presentation.presenters

import com.animalcatalog.domain.model.AnimalItem
import com.animalcatalog.domain.usecase.*
import com.animalcatalog.presentation.maincontract.MainContract
import com.animalcatalog.presentation.fragments.SelectedClassFragment

class SelectedClassFragmentPresenter(
    private val openDatabaseUseCase: OpenDatabaseUseCase,
    private val closeDatabaseUseCase: CloseDatabaseUseCase,
    private val readAnimalClassInDatabaseUseCase: ReadAnimalClassInDatabaseUseCase,
    private val addAnimalItemToFavoriteUseCase: AddAnimalItemToFavoriteUseCase,
    private val removeAnimalItemFromFavoriteUseCase: RemoveAnimalItemFromFavoriteUseCase
): MainContract.SelectedClassFragmentPresenterInterface {

    private var view: SelectedClassFragment? = null

    override fun attachView(selectedClassFragment: SelectedClassFragment) {
        view = selectedClassFragment
    }

    override fun openDatabase() {
        openDatabaseUseCase.openDatabase()
    }

    override fun closeDatabase() {
        closeDatabaseUseCase.closeDatabase()
    }

    override suspend fun readAnimalClassInDatabase(searchText: String): ArrayList<AnimalItem> {
        return readAnimalClassInDatabaseUseCase.readAnimalClassInDatabase(searchText)
    }

    override fun addAnimalItemToFavorite(id: Int, favorite: String) {
        addAnimalItemToFavoriteUseCase.addAnimalItemToFavorite(id, favorite)
    }

    override fun removeAnimalItemFromFavorite(id: Int, favorite: String) {
        removeAnimalItemFromFavoriteUseCase.removeAnimalItemFromFavorite(id, favorite)
    }

    override fun detachView() {
        view = null
    }
}