package com.example.country.view

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.country.R
import com.example.country.viewmodel.ListViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ListViewModel
    private val countryAdapter = CountryListAdapter(arrayListOf())
    private lateinit var rvList: RecyclerView
    private lateinit var tvLoadingError: TextView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvList = findViewById(R.id.rvList)
        tvLoadingError = findViewById(R.id.tvLoadingError)
        progressBar = findViewById(R.id.progressBar)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        rvList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countryAdapter
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.countries.observe(this, Observer { countries ->
            countries?.let {
                rvList.visibility = View.VISIBLE
                countryAdapter.updateCountries(it)
            }
        })

        viewModel.countryLoadError.observe(this, Observer { isError ->
            isError?.let { tvLoadingError.visibility = if (it) View.VISIBLE else View.GONE }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                progressBar.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    tvLoadingError.visibility = View.GONE
                    rvList.visibility = View.GONE
                }
            }
        })

    }

}


