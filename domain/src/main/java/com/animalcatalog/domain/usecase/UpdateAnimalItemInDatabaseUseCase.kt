package com.animalcatalog.domain.usecase

import com.animalcatalog.domain.repository.AnimalListRepository

class UpdateAnimalItemInDatabaseUseCase(private val animalListRepository: AnimalListRepository) {

    suspend fun updateAnimalItemInDatabase(id: Int, name: String, category: String, description: String, uri: String, time: String) {
        animalListRepository.updateAnimalItemInDatabase(id, name, category, description, uri, time)
    }
}