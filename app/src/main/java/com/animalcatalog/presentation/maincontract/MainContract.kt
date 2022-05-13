package com.animalcatalog.presentation.maincontract

import com.animalcatalog.domain.model.AnimalItem
import com.animalcatalog.presentation.EditAnimalActivity
import com.animalcatalog.presentation.MenuActivity
import com.animalcatalog.presentation.SplashActivity
import com.animalcatalog.presentation.fragments.*

interface MainContract {

    interface SearchAnimalFragmentPresenterInterface {

        fun attachView(searchAnimalFragment: SearchAnimalFragment)
        fun detachView()
        fun openDatabase()
        fun closeDatabase()
        suspend fun readDataInDatabase(searchText: String): ArrayList<AnimalItem>
        fun addAnimalItemToFavorite(id: Int, favorite: String)
        fun removeAnimalItemFromFavorite(id: Int, favorite: String)
    }

    interface ClassesAnimalFragmentPresenterInterface {

        fun attachView(classesAnimalFragment: ClassesAnimalFragment)
        fun detachView()
    }

    interface SelectedClassFragmentPresenterInterface {

        fun attachView(selectedClassFragment: SelectedClassFragment)
        fun detachView()
        fun openDatabase()
        fun closeDatabase()
        suspend fun readAnimalClassInDatabase(searchText: String): ArrayList<AnimalItem>
        fun addAnimalItemToFavorite(id: Int, favorite: String)
        fun removeAnimalItemFromFavorite(id: Int, favorite: String)
    }

    interface FavoritesAnimalFragmentPresenterInterface {

        fun attachView(favoritesAnimalFragment: FavoritesAnimalFragment)
        fun detachView()
        fun openDatabase()
        fun closeDatabase()
        suspend fun readAnimalFavoriteInDatabase(): ArrayList<AnimalItem>
        fun addAnimalItemToFavorite(id: Int, favorite: String)
        fun removeAnimalItemFromFavorite(id: Int, favorite: String)
    }

    interface EditAnimalActivityPresenterInterface {

        fun attachView(editAnimalActivity: EditAnimalActivity)
        fun detachView()
        fun openDatabase()
        fun closeDatabase()
        suspend fun addAnimalItemToDatabase(
            name: String,
            category: String,
            description: String,
            uri: String,
            time: String,
            favorite: String
        )

        suspend fun updateAnimalItemInDatabase(
            id: Int,
            name: String,
            category: String,
            description: String,
            uri: String,
            time: String
        )

        fun getTheCurrentAddTime(): String
    }

    interface CustomViewRequestPresenterInterface {

        fun attachView(customViewRequestFragment: CustomViewRequestFragment)
        fun detachView()
    }

    interface MenuActivityPresenterInterface {

        fun attachView(menuActivity: MenuActivity)
        fun detachView()
    }

    interface SplashActivityPresenterInterface {

        fun attachView(splashActivity: SplashActivity)
        fun detachView()
    }
}