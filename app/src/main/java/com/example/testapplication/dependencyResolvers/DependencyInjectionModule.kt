package com.example.testapplication.dependencyResolvers

import com.example.testapplication.services.abstracts.ICountryService
import com.example.testapplication.services.abstracts.IRickAndMortyService
import com.example.testapplication.services.abstracts.IUserService
import com.example.testapplication.services.concretes.CountryService
import com.example.testapplication.services.concretes.RickAndMortyService
import com.example.testapplication.services.concretes.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.annotation.Signed
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DependencyInjectionModule {

    @Provides
    @Singleton
    fun userServiceProvider() : IUserService {
        return UserService()
    }

    @Provides
    @Singleton
    fun rickAndMortyServiceProvider() : IRickAndMortyService {
        return RickAndMortyService()
    }

    @Provides
    @Singleton
    fun countryServiceProvider() : ICountryService {
        return CountryService()
    }
}