package com.example.testapplication.services.abstracts

import com.example.testapplication.models.Country
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET


interface ICountryService {
    @GET("countrydataset.json")
    fun getCountries() : Single<List<Country>>
}