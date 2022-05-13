package com.animalcatalog.domain.usecase

import com.animalcatalog.domain.model.AnimalItem
import com.animalcatalog.domain.repository.AnimalListRepository

class ReadAnimalFavoriteInDatabaseUseCase(private val animalListRepository: AnimalListRepository) {

    suspend fun readAnimalFavoriteInDatabase(): ArrayList<AnimalItem> {
        return animalListRepository.readAnimalFavoriteInDatabase()
    }
}