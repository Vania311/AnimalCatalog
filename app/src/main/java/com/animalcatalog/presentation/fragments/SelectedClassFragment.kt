package com.animalcatalog.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.animalcatalog.data.database.DatabaseManager
import com.animalcatalog.databinding.SelectedClassFragmentBinding
import com.animalcatalog.presentation.constants.IntentConstants
import com.animalcatalog.data.repositoryimpl.AnimalListRepositoryImpl
import com.animalcatalog.domain.model.AnimalItem
import com.animalcatalog.domain.usecase.*
import com.animalcatalog.presentation.interfaces.AddAndRemoveFavorite
import com.animalcatalog.presentation.EditAnimalActivity
import com.animalcatalog.presentation.maincontract.MainContract
import com.animalcatalog.presentation.interfaces.SetDataToAnimalItem
import com.animalcatalog.presentation.adapter.AnimalAdapter
import com.animalcatalog.presentation.basefragment.BaseFragment
import com.animalcatalog.presentation.presenters.SelectedClassFragmentPresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SelectedClassFragment: BaseFragment<SelectedClassFragmentBinding>(), SetDataToAnimalItem,
    AddAndRemoveFavorite {

    private val args: SelectedClassFragmentArgs by navArgs()

    private var presenter: MainContract.SelectedClassFragmentPresenterInterface? = null

    private val animalAdapter by lazy { AnimalAdapter( this,this, ArrayList()) }
    private val animalsStorage by lazy { DatabaseManager(requireActivity().applicationContext) }
    private val animalListRepository by lazy {
        AnimalListRepositoryImpl(
            animalsStorage
        ) }

    private val openDatabaseUseCase by lazy { OpenDatabaseUseCase(animalListRepository) }
    private val closeDatabaseUseCase by lazy { CloseDatabaseUseCase(animalListRepository) }
    private val readAnimalClassInDatabaseUseCase by lazy { ReadAnimalClassInDatabaseUseCase(animalListRepository) }
    private val removeAnimalItemFromDatabaseUseCase by lazy { RemoveAnimalItemFromDatabaseUseCase(animalListRepository) }
    private val addAnimalItemToFavoriteUseCase by lazy { AddAnimalItemToFavoriteUseCase(animalListRepository) }
    private val removeAnimalItemFromFavoriteUseCase by lazy { RemoveAnimalItemFromFavoriteUseCase(animalListRepository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = SelectedClassFragmentPresenter(
            openDatabaseUseCase,
            closeDatabaseUseCase,
            readAnimalClassInDatabaseUseCase,
            addAnimalItemToFavoriteUseCase,
            removeAnimalItemFromFavoriteUseCase)
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): SelectedClassFragmentBinding = SelectedClassFragmentBinding.inflate(inflater, container,false)

    override fun SelectedClassFragmentBinding.onBindView(savedInstanceState: Bundle?) {
        presenter?.attachView(this@SelectedClassFragment)
    }

    private fun connectAdapterAndSwapHelperToRecyclerView() {
        val swapHelper = getSwapManager()
        swapHelper.attachToRecyclerView(binding.classListRv)
        binding.classListRv.adapter = animalAdapter
    }

    private fun fillDatabaseWithAdapter() {

        CoroutineScope(Dispatchers.Main).launch {
        val selectedClass: String = args.inputClass
        val list = presenter!!.readAnimalClassInDatabase(selectedClass)
        animalAdapter.updateAnimalAdapter(list)
        }
    }

    private fun getSwapManager() : ItemTouchHelper {
        return ItemTouchHelper(object : ItemTouchHelper.
        SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                animalAdapter.removeAnimalItem(viewHolder.adapterPosition, removeAnimalItemFromDatabaseUseCase)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        connectAdapterAndSwapHelperToRecyclerView()
        presenter?.openDatabase()
        fillDatabaseWithAdapter()
    }

    override fun onDestroyView() {
        presenter?.closeDatabase()
        presenter?.detachView()
        super.onDestroyView()
    }

    override fun onDestroy() {
        presenter = null
        super.onDestroy()
    }

    override fun setDataToAnimalItem(animalItem: AnimalItem) {
        activity?.let {
            val intent = Intent(it, EditAnimalActivity::class.java)
            intent.putExtra(IntentConstants.I_ID_KEY, animalItem.id)
            intent.putExtra(IntentConstants.I_NAME_KEY, animalItem.name)
            intent.putExtra(IntentConstants.I_CATEGORY_KEY, animalItem.category)
            intent.putExtra(IntentConstants.I_DESCRIPTION_KEY, animalItem.description)
            intent.putExtra(IntentConstants.I_URI_KEY, animalItem.uri)
            it.startActivity(intent)
        }
    }

    override fun addFavorite(item: AnimalItem) {
        presenter?.addAnimalItemToFavorite(item.id, item.favorite)
    }

    override fun removeFavorite(item: AnimalItem) {
        presenter?.removeAnimalItemFromFavorite(item.id, item.favorite)
    }
}