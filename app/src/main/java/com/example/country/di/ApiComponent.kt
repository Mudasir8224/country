package com.example.country.di

import com.example.country.model.CountryService
import com.example.country.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service: CountryService)
    fun inject(viewModel: ListViewModel)
}