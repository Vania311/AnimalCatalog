package com.animalcatalog.domain.usecase

import com.animalcatalog.domain.model.AnimalItem
import com.animalcatalog.domain.repository.AnimalListRepository

class ReadAnimalClassInDatabaseUseCase(private val animalListRepository: AnimalListRepository) {

    suspend fun readAnimalClassInDatabase(animalClass: String): ArrayList<AnimalItem> {
        return animalListRepository.readAnimalClassInDatabase(animalClass)
    }
}