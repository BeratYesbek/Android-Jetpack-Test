package com.example.testapplication.services.concretes

import com.example.testapplication.models.Country
import com.example.testapplication.services.API
import com.example.testapplication.services.abstracts.ICountryService
import io.reactivex.rxjava3.core.Single

class CountryService : ICountryService {

    private val api = API.api<ICountryService>("https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/")

    override fun getCountries(): Single<List<Country>> {
        return api.getCountries()
    }
}