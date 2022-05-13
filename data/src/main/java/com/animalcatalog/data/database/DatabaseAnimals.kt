package com.animalcatalog.data.database

import android.provider.BaseColumns

object DatabaseAnimals: BaseColumns {

    const val TABLE_NAME = "animals_table"
    const val COLUMN_ANIMAL_NAME = "name"
    const val COLUMN_ANIMAL_CATEGORY = "category"
    const val COLUMN_ANIMAL_DESCRIPTION = "description"
    const val COLUMN_ANIMAL_IMAGE_URI = "uri"
    const val COLUMN_ANIMAL_TIME = "time"
    const val COLUMN_ANIMAL_FAVORITE = "favorite"

    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "Animals.db"

    const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "$COLUMN_ANIMAL_NAME TEXT," +
            "$COLUMN_ANIMAL_CATEGORY TEXT," +
            "$COLUMN_ANIMAL_DESCRIPTION TEXT," +
            "$COLUMN_ANIMAL_IMAGE_URI TEXT," +
            "$COLUMN_ANIMAL_TIME TEXT," +
            "$COLUMN_ANIMAL_FAVORITE)"

    const val SQL_DELETE_DATABASE = "DROP TABLE IF EXISTS $TABLE_NAME"
}