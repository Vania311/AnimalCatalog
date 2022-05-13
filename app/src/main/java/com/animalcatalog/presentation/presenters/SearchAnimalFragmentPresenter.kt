package com.animalcatalog.presentation.presenters

import com.animalcatalog.domain.model.AnimalItem
import com.animalcatalog.domain.usecase.*
import com.animalcatalog.presentation.maincontract.MainContract
import com.animalcatalog.presentation.fragments.SearchAnimalFragment

class SearchAnimalFragmentPresenter(
    private val openDatabaseUseCase: OpenDatabaseUseCase,
    private val closeDatabaseUseCase: CloseDatabaseUseCase,
    private val readDataInDatabaseUseCase: ReadDataInDatabaseUseCase,
    private val addAnimalItemToFavoriteUseCase: AddAnimalItemToFavoriteUseCase,
    private val removeAnimalItemFromFavoriteUseCase: RemoveAnimalItemFromFavoriteUseCase
): MainContract.SearchAnimalFragmentPresenterInterface {

    private var view: SearchAnimalFragment? = null

    override fun attachView(searchAnimalFragment: SearchAnimalFragment) {
        view = searchAnimalFragment
    }

    override fun openDatabase() {
        openDatabaseUseCase.openDatabase()
    }

    override fun closeDatabase() {
        closeDatabaseUseCase.closeDatabase()
    }

    override suspend fun readDataInDatabase(searchText: String): ArrayList<AnimalItem> {
        return readDataInDatabaseUseCase.readDataInDatabase(searchText)
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