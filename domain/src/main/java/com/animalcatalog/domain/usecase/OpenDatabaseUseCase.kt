package com.animalcatalog.domain.usecase

import com.animalcatalog.domain.repository.AnimalListRepository

class OpenDatabaseUseCase(private val animalListRepository: AnimalListRepository) {

    fun openDatabase() {
        animalListRepository.openDatabase()
    }
}