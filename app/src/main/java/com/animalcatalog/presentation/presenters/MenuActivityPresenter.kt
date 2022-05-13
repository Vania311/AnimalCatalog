package com.animalcatalog.presentation.presenters

import com.animalcatalog.presentation.MenuActivity
import com.animalcatalog.presentation.maincontract.MainContract

class MenuActivityPresenter: MainContract.MenuActivityPresenterInterface {

    private var view: MenuActivity? = null

    override fun attachView(menuActivity: MenuActivity) {
        view = menuActivity
    }

    override fun detachView() {
        view = null
    }
}