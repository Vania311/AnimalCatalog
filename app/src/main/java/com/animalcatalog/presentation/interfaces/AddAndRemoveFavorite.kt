package com.animalcatalog.presentation.interfaces

import com.animalcatalog.domain.model.AnimalItem

interface AddAndRemoveFavorite {

    fun addFavorite(item: AnimalItem)

    fun removeFavorite(item: AnimalItem)
}