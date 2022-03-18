package com.example.prueba.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.prueba.R
import com.example.prueba.controller.Controller
import com.example.prueba.databinding.ActivityMainBinding
import com.example.prueba.model.models.ResponseGetCountries
import com.example.prueba.view.adapter.CountriesAdapter

class MainActivity : AppCompatActivity(), Controller.CountriesCallbackListener {

    private lateinit var binding: ActivityMainBinding
    private var countriesAdapter: CountriesAdapter? = null
    private var controller: Controller? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        controller = Controller(this)

        controller?.getAccessToken()

        configViews()
    }

    private fun configViews() {
        showProgress()

        binding.btnSearchMain.setOnClickListener {
            filterCountryList()
        }

        binding.etSearchMain.doOnTextChanged { text, start, before, count ->
            countriesAdapter?.filterCountryList(text.toString())
        }

        countriesAdapter = CountriesAdapter()
        binding.recyclerMain.adapter = countriesAdapter

    }

    private fun filterCountryList() {
        val filter = binding.etSearchMain.text.toString()
        countriesAdapter?.filterCountryList(filter)
    }

    override fun onErrorGetAccessToken() {
        Toast.makeText(this, "Ha ocurrido un error al obtener el access token", Toast.LENGTH_LONG)
            .show()
        hideProgress()
    }

    override fun onGetAccessTokenSuccess(accessToken: String) {
        controller?.getCountries(accessToken)
    }

    override fun onGetCountriesSuccess(responseGetCountries: ResponseGetCountries) {
        hideProgress()
        countriesAdapter?.submitCountryList(responseGetCountries)
    }

    override fun onErrorGetCountries() {
        Toast.makeText(this, "Ha ocurrido un error al obtener los paises", Toast.LENGTH_LONG)
            .show()
        hideProgress()
    }

    private fun showProgress() {
        binding.progressMain.visibility = View.VISIBLE
        binding.recyclerMain.visibility = View.GONE
    }

    private fun hideProgress() {
        binding.progressMain.visibility = View.GONE
        binding.recyclerMain.visibility = View.VISIBLE
    }
}