package com.example.country.model

import com.example.country.di.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

class CountryService {

    @Inject
    lateinit var api: CountriesApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getCountries(): Single<List<Country>> {
        return api.getCountries()
    }

}