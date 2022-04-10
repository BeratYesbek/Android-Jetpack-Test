package com.example.testapplication.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testapplication.dao.CountryDao
import com.example.testapplication.database.TestDatabase
import com.example.testapplication.models.Country
import com.example.testapplication.services.abstracts.ICountryService
import com.example.testapplication.utilities.CustomSharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch

import javax.inject.Inject


@HiltViewModel
class FeedViewModel @Inject constructor(
    private val countryService: ICountryService,
    application: Application
) : BaseViewModel(application) {

    val countriesLiveData = MutableLiveData<List<Country>>()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()
    private val disposable = CompositeDisposable()
    private val customSharedPreferences = CustomSharedPreferences(getApplication())
    private var refreshTime = 10 * 60 * 1000 * 1000 * 1000L

    fun refreshData() {
        val updateTime = customSharedPreferences.getItem()
        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime) {
            getDataFromSqlLite()
        } else {
            getFromApi()
        }
    }

    private fun getFromApi() {
        disposable.add(
            countryService.getCountries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>() {
                    override fun onSuccess(t: List<Country>) {
                        storeInSQLite(t)
                        Toast.makeText(getApplication(),"Countries are coming from API",Toast.LENGTH_LONG).show()
                    }

                    override fun onError(e: Throwable) {
                        println(e.message)
                        loading.value = true
                        error.value = true
                        e.printStackTrace()
                    }

                })
        )
    }

    private fun getDataFromSqlLite() {
        launch {
            val countries = TestDatabase(getApplication()).countryDao().getAllCountries()
            showCountries(countries)
            Toast.makeText(getApplication(),"Countries are coming from SQLITE",Toast.LENGTH_LONG).show()


        }
    }

    private fun showCountries(countryList: List<Country>) {
        countriesLiveData.value = countryList
        error.value = false
        loading.value = false
    }

    private fun storeInSQLite(list: List<Country>) {
        launch {
            val dao = TestDatabase(getApplication()).countryDao()
            dao.deleteAllCountries()
            val listLong = dao.insertAll(*list.toTypedArray()) // -> list -> individual
            var i = 0
            while (i < list.size) {
                list[i].uuid = listLong[i].toInt()
                i += 1
            }

            showCountries(list)
        }

        customSharedPreferences.saveTime(System.nanoTime())
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}