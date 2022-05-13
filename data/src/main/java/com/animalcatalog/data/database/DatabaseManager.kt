package com.animalcatalog.data.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import com.animalcatalog.data.AnimalsStorage
import com.animalcatalog.domain.model.AnimalItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DatabaseManager(context: Context) : AnimalsStorage {

    private val databaseHelper = DatabaseHelper(context)
    private var database: SQLiteDatabase? = null

    override fun openDatabase() {
        database = databaseHelper.writableDatabase
    }

    override fun closeDatabase() {
        databaseHelper.close()
    }

    override suspend fun addAnimalItemToDatabase(
        name: String,
        category: String,
        description: String,
        uri: String,
        time: String,
        favorite: String
    ): Unit = withContext(Dispatchers.IO) {
        val values = ContentValues().apply {
            put(DatabaseAnimals.COLUMN_ANIMAL_NAME, name)
            put(DatabaseAnimals.COLUMN_ANIMAL_CATEGORY, category)
            put(DatabaseAnimals.COLUMN_ANIMAL_DESCRIPTION, description)
            put(DatabaseAnimals.COLUMN_ANIMAL_IMAGE_URI, uri)
            put(DatabaseAnimals.COLUMN_ANIMAL_TIME, time)
            put(DatabaseAnimals.COLUMN_ANIMAL_FAVORITE, favorite)
        }
        database?.insert(DatabaseAnimals.TABLE_NAME, null, values)
    }

    override suspend fun updateAnimalItemInDatabase(
        id: Int,
        name: String,
        category: String,
        description: String,
        uri: String,
        time: String
    ): Unit = withContext(Dispatchers.IO) {
        val selection = BaseColumns._ID + "=$id"

        val values = ContentValues().apply {
            put(DatabaseAnimals.COLUMN_ANIMAL_NAME, name)
            put(DatabaseAnimals.COLUMN_ANIMAL_CATEGORY, category)
            put(DatabaseAnimals.COLUMN_ANIMAL_DESCRIPTION, description)
            put(DatabaseAnimals.COLUMN_ANIMAL_IMAGE_URI, uri)
            put(DatabaseAnimals.COLUMN_ANIMAL_TIME, time)
        }
        database?.update(DatabaseAnimals.TABLE_NAME, values, selection, null)
    }

    override fun removeAnimalItemFromDatabase(id: String) {
        val selection = BaseColumns._ID + "=$id"
        database?.delete(DatabaseAnimals.TABLE_NAME, selection, null)
    }

    @SuppressLint("Range")
    override suspend fun readDataInDatabase(searchText: String): ArrayList<AnimalItem> =
        withContext(Dispatchers.IO) {
        val dataList = ArrayList<AnimalItem>()
        val selection = "${DatabaseAnimals.COLUMN_ANIMAL_NAME} like ?"
        val cursor = database?.query(
            DatabaseAnimals.TABLE_NAME,
            null, selection, arrayOf("%$searchText%"), null, null, null
        )

        while (cursor?.moveToNext()!!) {
            val dataId = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
            val dataName = cursor.getString(cursor.getColumnIndex(DatabaseAnimals.COLUMN_ANIMAL_NAME))
            val dataCategory = cursor.getString(cursor.getColumnIndex(DatabaseAnimals.COLUMN_ANIMAL_CATEGORY))
            val dataDescription = cursor.getString(cursor.getColumnIndex(DatabaseAnimals.COLUMN_ANIMAL_DESCRIPTION))
            val dataUri = cursor.getString(cursor.getColumnIndex(DatabaseAnimals.COLUMN_ANIMAL_IMAGE_URI))
            val dataTime = cursor.getString(cursor.getColumnIndex(DatabaseAnimals.COLUMN_ANIMAL_TIME))
            val dataFavorite = cursor.getString(cursor.getColumnIndex(DatabaseAnimals.COLUMN_ANIMAL_FAVORITE))
            val item = AnimalItem()
            item.id = dataId
            item.name = dataName
            item.category = dataCategory
            item.description = dataDescription
            item.uri = dataUri
            item.time = dataTime
            item.favorite = dataFavorite

            dataList.add(item)
        }
        cursor.close()
        return@withContext dataList
    }

    @SuppressLint("Range")
    override suspend fun readAnimalClassInDatabase(animalClass: String): ArrayList<AnimalItem> =
        withContext(Dispatchers.IO) {
        val dataList = ArrayList<AnimalItem>()
        val projection = arrayOf(
            BaseColumns._ID,
            DatabaseAnimals.COLUMN_ANIMAL_NAME,
            DatabaseAnimals.COLUMN_ANIMAL_CATEGORY,
            DatabaseAnimals.COLUMN_ANIMAL_DESCRIPTION,
            DatabaseAnimals.COLUMN_ANIMAL_IMAGE_URI,
            DatabaseAnimals.COLUMN_ANIMAL_FAVORITE)
        val selection = "${DatabaseAnimals.COLUMN_ANIMAL_CATEGORY} = ?"
        val selectionArgs = arrayOf(animalClass)
        val cursor = database?.query(
            DatabaseAnimals.TABLE_NAME,
            projection, selection, selectionArgs, null, null, null
        )

        while (cursor?.moveToNext()!!) {
            val dataId = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
            val dataName = cursor.getString(cursor.getColumnIndex(DatabaseAnimals.COLUMN_ANIMAL_NAME))
            val dataCategory = cursor.getString(cursor.getColumnIndex(DatabaseAnimals.COLUMN_ANIMAL_CATEGORY))
            val dataDescription = cursor.getString(cursor.getColumnIndex(DatabaseAnimals.COLUMN_ANIMAL_DESCRIPTION))
            val dataUri = cursor.getString(cursor.getColumnIndex(DatabaseAnimals.COLUMN_ANIMAL_IMAGE_URI))
            val dataFavorite = cursor.getString(cursor.getColumnIndex(DatabaseAnimals.COLUMN_ANIMAL_FAVORITE))
            val item = AnimalItem()
            item.id = dataId
            item.name = dataName
            item.category = dataCategory
            item.description = dataDescription
            item.uri = dataUri
            item.favorite = dataFavorite

            dataList.add(item)
        }
        cursor.close()
            return@withContext dataList
    }

    override fun addAnimalItemToFavorite(
        id: Int,
        favorite: String
    ) {
            val selection = BaseColumns._ID + "=$id"

            val values = ContentValues().apply {
                put(DatabaseAnimals.COLUMN_ANIMAL_FAVORITE, AnimalItem.CASE_ANIMAL_FAVORITE_TRUE)
            }
        database?.update(DatabaseAnimals.TABLE_NAME, values, selection, null)

    }

    override fun removeAnimalItemFromFavorite(
        id: Int,
        favorite: String
    ) {
        val selection = BaseColumns._ID + "=$id"

        val values = ContentValues().apply {
            put(DatabaseAnimals.COLUMN_ANIMAL_FAVORITE, AnimalItem.CASE_ANIMAL_FAVORITE_FALSE)
        }
        database?.update(DatabaseAnimals.TABLE_NAME, values, selection, null)

    }

    @SuppressLint("Range")
    override suspend fun readAnimalFavoriteInDatabase(): ArrayList<AnimalItem> =
        withContext(Dispatchers.IO) {
        val dataList = ArrayList<AnimalItem>()
        val projection = arrayOf(
            BaseColumns._ID,
            DatabaseAnimals.COLUMN_ANIMAL_NAME,
            DatabaseAnimals.COLUMN_ANIMAL_CATEGORY,
            DatabaseAnimals.COLUMN_ANIMAL_DESCRIPTION,
            DatabaseAnimals.COLUMN_ANIMAL_IMAGE_URI,
            DatabaseAnimals.COLUMN_ANIMAL_FAVORITE)
        val selection = "${DatabaseAnimals.COLUMN_ANIMAL_FAVORITE} = ?"
        val selectionArgs = arrayOf("true")
        val cursor = database?.query(
            DatabaseAnimals.TABLE_NAME,
            projection, selection, selectionArgs, null, null, null
        )

        while (cursor?.moveToNext()!!) {
            val dataId = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
            val dataName = cursor.getString(cursor.getColumnIndex(DatabaseAnimals.COLUMN_ANIMAL_NAME))
            val dataCategory = cursor.getString(cursor.getColumnIndex(DatabaseAnimals.COLUMN_ANIMAL_CATEGORY))
            val dataDescription = cursor.getString(cursor.getColumnIndex(DatabaseAnimals.COLUMN_ANIMAL_DESCRIPTION))
            val dataUri = cursor.getString(cursor.getColumnIndex(DatabaseAnimals.COLUMN_ANIMAL_IMAGE_URI))
            val dataFavorite = cursor.getString(cursor.getColumnIndex(DatabaseAnimals.COLUMN_ANIMAL_FAVORITE))
            val item = AnimalItem()
            item.id = dataId
            item.name = dataName
            item.category = dataCategory
            item.description = dataDescription
            item.uri = dataUri
            item.favorite = dataFavorite

            dataList.add(item)
        }
        cursor.close()
            return@withContext dataList
    }
}