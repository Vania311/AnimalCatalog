package com.animalcatalog.domain.usecase

import com.animalcatalog.domain.repository.AnimalListRepository

class CloseDatabaseUseCase(private val animalListRepository: AnimalListRepository) {

    fun closeDatabase() {
        animalListRepository.closeDatabase()
    }
}