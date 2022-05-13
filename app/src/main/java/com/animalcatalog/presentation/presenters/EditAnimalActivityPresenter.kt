package com.animalcatalog.presentation.presenters

import com.animalcatalog.domain.usecase.*
import com.animalcatalog.presentation.EditAnimalActivity
import com.animalcatalog.presentation.maincontract.MainContract

class EditAnimalActivityPresenter(
    private val openDatabaseUseCase: OpenDatabaseUseCase,
    private val closeDatabaseUseCase: CloseDatabaseUseCase,
    private val addAnimalItemToDatabaseUseCase: AddAnimalItemToDatabaseUseCase,
    private val updateAnimalItemInDatabaseUseCase: UpdateAnimalItemInDatabaseUseCase,
    private val getTheCurrentAddTimeUseCase: GetTheCurrentAddTimeUseCase
): MainContract.EditAnimalActivityPresenterInterface  {

    private var view: EditAnimalActivity? = null

    override fun attachView(editAnimalActivity: EditAnimalActivity) {
        view = editAnimalActivity
    }

    override fun detachView() {
        view = null
    }

    override fun openDatabase() {
        openDatabaseUseCase.openDatabase()
    }

    override fun closeDatabase() {
        closeDatabaseUseCase.closeDatabase()
    }

    override suspend fun addAnimalItemToDatabase(
        name: String,
        category: String,
        description: String,
        uri: String,
        time: String,
        favorite: String
    ) {
        addAnimalItemToDatabaseUseCase.addAnimalItemToDatabase(name, category, description, uri, time, favorite)
    }

    override suspend fun updateAnimalItemInDatabase(
        id: Int,
        name: String,
        category: String,
        description: String,
        uri: String,
        time: String
    ) {
        updateAnimalItemInDatabaseUseCase.updateAnimalItemInDatabase(id, name, category, description, uri, time)
    }

    override fun getTheCurrentAddTime(): String {
        return getTheCurrentAddTimeUseCase.getTheCurrentAddTime()
    }
}