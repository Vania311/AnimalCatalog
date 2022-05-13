package com.animalcatalog.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.animalcatalog.databinding.ClassesAnimalFragmentBinding
import com.animalcatalog.presentation.maincontract.MainContract
import com.animalcatalog.presentation.basefragment.BaseFragment
import com.animalcatalog.presentation.constants.AnimalConstants
import com.animalcatalog.presentation.presenters.ClassesAnimalFragmentPresenter

class ClassesAnimalFragment: BaseFragment<ClassesAnimalFragmentBinding>() {

    private var presenter: MainContract.ClassesAnimalFragmentPresenterInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = ClassesAnimalFragmentPresenter()
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ClassesAnimalFragmentBinding = ClassesAnimalFragmentBinding.inflate(inflater, container,false)

    override fun ClassesAnimalFragmentBinding.onBindView(savedInstanceState: Bundle?) {
        presenter?.attachView(this@ClassesAnimalFragment)

        amphibiansCardView.setOnClickListener {
            navController.navigate(
                ClassesAnimalFragmentDirections.navigateToSelectedClassFragment(
                    AnimalConstants.AMPHIBIANS
                )
            )
        }

        mammalsCardView.setOnClickListener {
            navController.navigate(
                ClassesAnimalFragmentDirections.navigateToSelectedClassFragment(
                    AnimalConstants.MAMMALS
                )
            )
        }

        insectsCardView.setOnClickListener {
            navController.navigate(
                ClassesAnimalFragmentDirections.navigateToSelectedClassFragment(
                    AnimalConstants.INSECTS
                )
            )
        }

        arachnidsCardView.setOnClickListener {
            navController.navigate(
                ClassesAnimalFragmentDirections.navigateToSelectedClassFragment(
                    AnimalConstants.ARACHNIDS
                )
            )
        }

        reptilesCardView.setOnClickListener {
            navController.navigate(
                ClassesAnimalFragmentDirections.navigateToSelectedClassFragment(
                    AnimalConstants.REPTILES
                )
            )
        }

        birdsCardView.setOnClickListener {
            navController.navigate(
                ClassesAnimalFragmentDirections.navigateToSelectedClassFragment(
                    AnimalConstants.BIRDS
                )
            )
        }

        crustaceansCardView.setOnClickListener {
            navController.navigate(
                ClassesAnimalFragmentDirections.navigateToSelectedClassFragment(
                    AnimalConstants.CRUSTACEANS
                )
            )
        }

        fishesCardView.setOnClickListener {
            navController.navigate(
                ClassesAnimalFragmentDirections.navigateToSelectedClassFragment(
                    AnimalConstants.FISH
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter?.detachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter = null
    }
}