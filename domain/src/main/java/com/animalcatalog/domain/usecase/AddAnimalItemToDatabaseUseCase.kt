package com.animalcatalog.domain.usecase

import com.animalcatalog.domain.repository.AnimalListRepository

class AddAnimalItemToDatabaseUseCase(private val animalListRepository: AnimalListRepository) {

    suspend fun addAnimalItemToDatabase(name: String, category: String, description: String, uri: String, time: String, favorite: String) {
        animalListRepository.addAnimalItemToDatabase(name, category, description, uri, time, favorite)
    }
}