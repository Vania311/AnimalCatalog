package com.animalcatalog.domain.repository

import com.animalcatalog.domain.model.AnimalItem

interface AnimalListRepository {

    fun openDatabase()

    fun closeDatabase()

    suspend fun addAnimalItemToDatabase(
        name: String,
        category: String,
        description: String,
        uri: String,
        time: String,
        favorite: String)

    suspend fun updateAnimalItemInDatabase(
        id: Int,
        name: String,
        category: String,
        description: String,
        uri: String,
        time: String)

    suspend fun readDataInDatabase(searchText: String): ArrayList<AnimalItem>

    suspend fun readAnimalClassInDatabase(animalClass: String): ArrayList<AnimalItem>

    suspend fun readAnimalFavoriteInDatabase(): ArrayList<AnimalItem>

    fun addAnimalItemToFavorite(id: Int, favorite: String)

    fun removeAnimalItemFromFavorite(id: Int, favorite: String)

    fun removeAnimalItemFromDatabase(id: String)

    fun getTheCurrentAddTime(): String
}