package com.animalcatalog.presentation.presenters

import com.animalcatalog.presentation.SplashActivity
import com.animalcatalog.presentation.maincontract.MainContract

class SplashActivityPresenter: MainContract.SplashActivityPresenterInterface {

    private var view: SplashActivity? = null

    override fun attachView(splashActivity: SplashActivity) {
        view = splashActivity
    }

    override fun detachView() {
        view = null
    }
}