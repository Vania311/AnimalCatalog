package com.animalcatalog.presentation.presenters

import com.animalcatalog.presentation.maincontract.MainContract
import com.animalcatalog.presentation.fragments.ClassesAnimalFragment

class ClassesAnimalFragmentPresenter: MainContract.ClassesAnimalFragmentPresenterInterface {

    private var view: ClassesAnimalFragment? = null

    override fun attachView(classesAnimalFragment: ClassesAnimalFragment) {
        view = classesAnimalFragment
    }

    override fun detachView() {
        view = null
    }
}