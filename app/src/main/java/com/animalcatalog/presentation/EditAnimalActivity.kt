package com.animalcatalog.presentation

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.animalcatalog.data.database.DatabaseManager
import com.animalcatalog.presentation.constants.IntentConstants
import com.animalcatalog.data.repositoryimpl.AnimalListRepositoryImpl
import com.animalcatalog.databinding.EditAnimalActivityBinding
import com.animalcatalog.domain.model.AnimalItem
import com.animalcatalog.domain.usecase.*
import com.animalcatalog.presentation.maincontract.MainContract
import com.animalcatalog.presentation.presenters.EditAnimalActivityPresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditAnimalActivity : AppCompatActivity() {

    private var _binding: EditAnimalActivityBinding? = null
    private val binding get() = _binding!!

    private var presenter: MainContract.EditAnimalActivityPresenterInterface? = null

    var id = 0

    private var isEditState = false
    private val imageRequestCode = 1
    private var tempImageUri = "empty"

    private val animalsStorage by lazy { DatabaseManager(this) }
    private val animalListRepository by lazy {
        AnimalListRepositoryImpl(
            animalsStorage
        )
    }

    private val openDatabaseUseCase by lazy { OpenDatabaseUseCase(animalListRepository) }
    private val closeDatabaseUseCase by lazy { CloseDatabaseUseCase(animalListRepository) }
    private val addAnimalItemToDatabaseUseCase by lazy { AddAnimalItemToDatabaseUseCase(animalListRepository) }
    private val updateAnimalItemInDatabaseUseCase by lazy { UpdateAnimalItemInDatabaseUseCase(animalListRepository) }
    private val getCurrentTimeUseCase by lazy { GetTheCurrentAddTimeUseCase(animalListRepository) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = EditAnimalActivityPresenter(
            openDatabaseUseCase,
            closeDatabaseUseCase,
            addAnimalItemToDatabaseUseCase,
            updateAnimalItemInDatabaseUseCase,
            getCurrentTimeUseCase
        )
        _binding = EditAnimalActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter?.attachView(this)

        getDataFromAnimalItem()


        // showImageLayoutAndHideAddImageButton
        with(binding) {
            addImageIb.setOnClickListener {
                animalImageLayout.visibility = View.VISIBLE
                addImageIb.visibility = View.GONE
            }
        }

        // chooseImage
        with(binding) {
            editImageIb.setOnClickListener {
                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                intent.type = "image/*"
                startActivityForResult(intent, imageRequestCode)
            }
        }


        // deleteImage
        with(binding) {
        deleteImageIb.setOnClickListener {
            animalImageLayout.visibility = View.GONE
            addImageIb.visibility = View.VISIBLE
            saveIb.visibility = View.VISIBLE
            editIb.visibility = View.GONE
            tempImageUri = "empty"
        }
        }

        // editAnimalItem
        with(binding) {
            editIb.setOnClickListener {
            classesTextview.visibility = View.VISIBLE
            classesSpinner.visibility = View.VISIBLE

                nameEdittext.isEnabled = true
                classesSpinner.isEnabled = true
                descriptionEdittext.isEnabled = true
                editIb.visibility = View.GONE
                addImageIb.visibility = View.VISIBLE
                saveIb.visibility = View.VISIBLE

                if (tempImageUri == "empty") return@setOnClickListener
                else addImageIb.visibility = View.GONE
                editImageIb.visibility = View.VISIBLE
                deleteImageIb.visibility = View.VISIBLE
            }
        }

        with(binding) {
        saveIb.setOnClickListener {
            val myName = nameEdittext.text.toString()
            val myCategory = classesSpinner.selectedItem.toString()
            val myContent = descriptionEdittext.text.toString()
            val myFavorite = AnimalItem.CASE_ANIMAL_FAVORITE_FALSE

            if (myName != "" && myContent != "") {

                CoroutineScope(Dispatchers.Main).launch {

                    if (isEditState) {
                        presenter?.updateAnimalItemInDatabase(
                            id,
                            myName,
                            myCategory,
                            myContent,
                            tempImageUri,
                            presenter!!.getTheCurrentAddTime()
                        )
                    } else {
                        presenter?.addAnimalItemToDatabase(
                            myName,
                            myCategory,
                            myContent,
                            tempImageUri,
                            presenter!!.getTheCurrentAddTime(),
                            myFavorite
                        )
                    }
                    finish()
                }
                Toast.makeText(this@EditAnimalActivity, "Сохранено!", Toast.LENGTH_SHORT).show()
            } else Toast.makeText(this@EditAnimalActivity, "Пустые поля!", Toast.LENGTH_SHORT).show()
        }
    }
    }

    // displayImageByLink
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == imageRequestCode) {
            binding.animalImageIv.setImageURI(data?.data)
            tempImageUri = data?.data.toString()
            contentResolver.takePersistableUriPermission(data?.data!!, Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
    }

    private fun getDataFromAnimalItem() {

        with(binding) {
        editIb.visibility = View.GONE
        classesTextview.visibility = View.VISIBLE
        classesSpinner.visibility = View.VISIBLE
        }

        val i = intent

        if (i != null) {

            if (i.getStringExtra(IntentConstants.I_NAME_KEY) != null) {

                with(binding) {

                    addImageIb.visibility = View.GONE
                    saveIb.visibility = View.GONE

                    classesTextview.visibility = View.GONE
                    classesSpinner.visibility = View.GONE

                    nameEdittext.setText(i.getStringExtra(IntentConstants.I_NAME_KEY))
                    isEditState = true
                    nameEdittext.isEnabled = false
                    classesSpinner.isEnabled = false
                    descriptionEdittext.isEnabled = true
                    editIb.visibility = View.VISIBLE

                    classesSpinner.setSelection(i.getIntExtra(IntentConstants.I_CATEGORY_KEY, 0))
                    descriptionEdittext.setText(i.getStringExtra(IntentConstants.I_DESCRIPTION_KEY))
                    id = i.getIntExtra(IntentConstants.I_ID_KEY, 0)
                }

                if (i.getStringExtra(IntentConstants.I_URI_KEY) != "empty") {

                    with(binding) {
                        animalImageLayout.visibility = View.VISIBLE
                        tempImageUri = i.getStringExtra(IntentConstants.I_URI_KEY)!!
                        animalImageIv.setImageURI(Uri.parse(tempImageUri))
                        editImageIb.visibility = View.GONE
                        deleteImageIb.visibility = View.GONE
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        presenter?.openDatabase()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.closeDatabase()
        presenter?.detachView()
        presenter = null
        _binding = null
    }
}