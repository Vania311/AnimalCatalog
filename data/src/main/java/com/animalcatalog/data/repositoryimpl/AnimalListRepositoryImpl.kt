package com.animalcatalog.data.repositoryimpl

import com.animalcatalog.data.database.DatabaseManager
import com.animalcatalog.domain.repository.AnimalListRepository
import com.animalcatalog.domain.model.AnimalItem
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AnimalListRepositoryImpl(
    private val databaseManager: DatabaseManager
): AnimalListRepository {

    override fun openDatabase() {
        databaseManager.openDatabase()
    }

    override fun closeDatabase() {
        databaseManager.closeDatabase()
    }

    override suspend fun addAnimalItemToDatabase(
        name: String,
        category: String,
        description: String,
        uri: String,
        time: String,
        favorite: String
    ) {
        databaseManager.addAnimalItemToDatabase(name, category, description, uri, time, favorite)
    }

     override suspend fun updateAnimalItemInDatabase(
         id: Int,
         name: String,
         category: String,
         description: String,
         uri: String,
         time: String
     ) {
        databaseManager.updateAnimalItemInDatabase(id, name, category, description, uri, time)
    }

    override suspend fun readDataInDatabase(searchText: String): ArrayList<AnimalItem> {
        return databaseManager.readDataInDatabase(searchText)
    }

    override suspend fun readAnimalClassInDatabase(animalClass: String): ArrayList<AnimalItem> {
        return databaseManager.readAnimalClassInDatabase(animalClass)
    }

    override suspend fun readAnimalFavoriteInDatabase(): ArrayList<AnimalItem> {
        return databaseManager.readAnimalFavoriteInDatabase()
    }

    override fun removeAnimalItemFromDatabase(id: String) {
        databaseManager.removeAnimalItemFromDatabase(id)
    }

    override fun getTheCurrentAddTime(): String {
        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("dd–MM–yy kk:mm", Locale.getDefault())
        val fTime = formatter.format(time)
        return fTime.toString()
    }

    override fun addAnimalItemToFavorite(id: Int, favorite: String) {
        databaseManager.addAnimalItemToFavorite(id, favorite)
    }

    override fun removeAnimalItemFromFavorite(id: Int, favorite: String) {
        databaseManager.removeAnimalItemFromFavorite(id, favorite)
    }
}