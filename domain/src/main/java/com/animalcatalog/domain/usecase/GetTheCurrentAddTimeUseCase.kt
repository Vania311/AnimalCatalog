package com.animalcatalog.domain.usecase

import com.animalcatalog.domain.repository.AnimalListRepository

class GetTheCurrentAddTimeUseCase(private val animalListRepository: AnimalListRepository) {

    fun getTheCurrentAddTime(): String {
        return  animalListRepository.getTheCurrentAddTime()
    }
}