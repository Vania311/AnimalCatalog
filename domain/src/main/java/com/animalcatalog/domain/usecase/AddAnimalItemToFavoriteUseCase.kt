package com.animalcatalog.domain.usecase

import com.animalcatalog.domain.repository.AnimalListRepository

class AddAnimalItemToFavoriteUseCase(private val animalListRepository: AnimalListRepository) {

    fun addAnimalItemToFavorite(id: Int, favorite: String) {
        animalListRepository.addAnimalItemToFavorite(id, favorite)
    }
}