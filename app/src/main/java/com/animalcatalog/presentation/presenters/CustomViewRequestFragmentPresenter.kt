package com.animalcatalog.presentation.presenters

import com.animalcatalog.presentation.fragments.CustomViewRequestFragment
import com.animalcatalog.presentation.maincontract.MainContract

class CustomViewRequestFragmentPresenter: MainContract.CustomViewRequestPresenterInterface {

    private var view: CustomViewRequestFragment? = null

    override fun attachView(customViewRequestFragment: CustomViewRequestFragment) {
        view = customViewRequestFragment
    }

    override fun detachView() {
        view = null
    }
}