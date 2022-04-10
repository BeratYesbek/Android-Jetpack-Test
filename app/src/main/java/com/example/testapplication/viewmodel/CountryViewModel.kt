package com.example.testapplication.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.testapplication.database.TestDatabase
import com.example.testapplication.models.Country
import kotlinx.coroutines.launch

class CountryViewModel(application: Application) : BaseViewModel(application) {

    private val countryDao = TestDatabase(getApplication()).countryDao()
    val countryLiveData = MutableLiveData<Country>()

    fun getDataFromSqlLite(uuid : Int) {
        launch {
            val country = countryDao.getCountry(uuid)
            countryLiveData.value = country
        }
    }
}