package com.animalcatalog.domain.usecase

import com.animalcatalog.domain.repository.AnimalListRepository

class RemoveAnimalItemFromDatabaseUseCase(private val animalListRepository: AnimalListRepository) {

    fun removeAnimalItemFromDatabase(id: String) {
        animalListRepository.removeAnimalItemFromDatabase(id)
    }
}