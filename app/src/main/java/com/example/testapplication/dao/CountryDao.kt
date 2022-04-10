package com.example.testapplication.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.testapplication.models.Country

@Dao
interface CountryDao {

    @Insert
    suspend fun insertAll(vararg countries : Country) : List<Long>

    @Query("SELECT * FROM country")
    suspend fun getAllCountries() : List<Country>

    @Query("SELECT * FROM country WHERE uuid = :countryId")
    suspend fun getCountry(countryId: Int) : Country

    @Query("DELETE FROM country")
    suspend fun deleteAllCountries()
}