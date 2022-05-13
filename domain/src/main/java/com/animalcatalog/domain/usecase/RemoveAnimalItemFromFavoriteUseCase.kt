package com.animalcatalog.domain.usecase

import com.animalcatalog.domain.repository.AnimalListRepository

class RemoveAnimalItemFromFavoriteUseCase(private val animalListRepository: AnimalListRepository) {

    fun removeAnimalItemFromFavorite(id: Int, favorite: String) {
        animalListRepository.removeAnimalItemFromFavorite(id, favorite)
    }
}