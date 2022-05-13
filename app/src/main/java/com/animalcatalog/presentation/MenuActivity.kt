package com.animalcatalog.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.animalcatalog.R
import com.animalcatalog.databinding.MenuActivityBinding
import com.animalcatalog.presentation.maincontract.MainContract
import com.animalcatalog.presentation.presenters.MenuActivityPresenter

class MenuActivity: AppCompatActivity() {

    private var _binding: MenuActivityBinding? = null
    private val binding get() = _binding!!

    private var presenter: MainContract.MenuActivityPresenterInterface? = null

    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = MenuActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = MenuActivityPresenter()
        presenter?.attachView(this)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        navigationController = navHostFragment.navController

        binding.navigationMenu.apply {
            setupWithNavController(navigationController)
            setOnItemSelectedListener { item ->
                NavigationUI.onNavDestinationSelected(item, navigationController)
                navigationController.popBackStack(item.itemId, inclusive = false)
                true
            }
        }
    }

   override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
       super.onActivityResult(requestCode, resultCode, data)
       for (fragment in supportFragmentManager.fragments) {
           fragment.onActivityResult(requestCode, resultCode, data)
       }
   }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.detachView()
        presenter = null
        _binding = null
    }
}