package com.animalcatalog.presentation.presenters

import com.animalcatalog.domain.model.AnimalItem
import com.animalcatalog.domain.usecase.*
import com.animalcatalog.presentation.maincontract.MainContract
import com.animalcatalog.presentation.fragments.FavoritesAnimalFragment

class FavoritesAnimalFragmentPresenter(
    private val openDatabaseUseCase: OpenDatabaseUseCase,
    private val closeDatabaseUseCase: CloseDatabaseUseCase,
    private val readAnimalFavoriteInDatabaseUseCase: ReadAnimalFavoriteInDatabaseUseCase,
    private val addAnimalItemToFavoriteUseCase: AddAnimalItemToFavoriteUseCase,
    private val removeAnimalItemFromFavoriteUseCase: RemoveAnimalItemFromFavoriteUseCase
): MainContract.FavoritesAnimalFragmentPresenterInterface {

    private var view: FavoritesAnimalFragment? = null

    override fun attachView(favoritesAnimalFragment: FavoritesAnimalFragment) {
        view = favoritesAnimalFragment
    }

    override fun openDatabase() {
        openDatabaseUseCase.openDatabase()
    }

    override fun closeDatabase() {
        closeDatabaseUseCase.closeDatabase()
    }

    override suspend fun readAnimalFavoriteInDatabase(): ArrayList<AnimalItem> {
        return readAnimalFavoriteInDatabaseUseCase.readAnimalFavoriteInDatabase()
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