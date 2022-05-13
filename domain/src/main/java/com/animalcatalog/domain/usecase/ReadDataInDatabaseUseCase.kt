package com.animalcatalog.domain.usecase

import com.animalcatalog.domain.model.AnimalItem
import com.animalcatalog.domain.repository.AnimalListRepository

class ReadDataInDatabaseUseCase(private val animalListRepository: AnimalListRepository) {

    suspend fun readDataInDatabase(searchText: String): ArrayList<AnimalItem> {
        return animalListRepository.readDataInDatabase(searchText)
    }
}