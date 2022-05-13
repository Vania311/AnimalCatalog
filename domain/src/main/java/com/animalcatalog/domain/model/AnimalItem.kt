package com.animalcatalog.domain.model

data class AnimalItem(

    var id: Int = DEFAULT_ANIMAL_ID,
    var name: String = DEFAULT_ANIMAL_NAME,
    var category: String = DEFAULT_ANIMAL_CATEGORY,
    var description: String = DEFAULT_ANIMAL_DESCRIPTION,
    var uri: String = DEFAULT_ANIMAL_URI,
    var time: String = DEFAULT_ANIMAL_TIME,
    var favorite: String = CASE_ANIMAL_FAVORITE_FALSE
) {
    companion object {

        const val DEFAULT_ANIMAL_ID = 0
        const val DEFAULT_ANIMAL_NAME = "empty"
        const val DEFAULT_ANIMAL_CATEGORY = "Земноводные"
        const val DEFAULT_ANIMAL_DESCRIPTION = "empty"
        const val DEFAULT_ANIMAL_URI = "empty"
        const val DEFAULT_ANIMAL_TIME = ""
        const val CASE_ANIMAL_FAVORITE_FALSE = "false"
        const val CASE_ANIMAL_FAVORITE_TRUE = "true"
    }
}
