package com.example.testapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testapplication.dao.CountryDao
import com.example.testapplication.models.Country

@Database(entities = [Country::class], version = 1)
abstract class TestDatabase : RoomDatabase() {

    abstract fun countryDao() : CountryDao


    companion object {
        @Volatile private var instance : TestDatabase? = null

        private val lock = Any()

        operator fun invoke(context : Context) = instance ?: synchronized(lock) {
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context : Context) = Room.databaseBuilder(
            context.applicationContext,TestDatabase::class.java,"test_database"
        ).build()
    }
}