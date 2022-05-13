package com.animalcatalog.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.animalcatalog.databinding.SplashActivityBinding
import com.animalcatalog.presentation.extension.showActivityMenu
import com.animalcatalog.presentation.maincontract.MainContract
import com.animalcatalog.presentation.presenters.SplashActivityPresenter

@SuppressLint("CustomSplashScreen")
class SplashActivity: AppCompatActivity() {

    private var _binding: SplashActivityBinding? = null
    private val binding get() = _binding!!

    private var presenter: MainContract.SplashActivityPresenterInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = SplashActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = SplashActivityPresenter()
        presenter?.attachView(this)

        binding.splashLinearLayout.alpha = 0f
        binding.splashLinearLayout.animate().setDuration(2000).alpha(1f).withEndAction {
                showActivityMenu()
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.detachView()
        presenter = null
        _binding = null
    }
}