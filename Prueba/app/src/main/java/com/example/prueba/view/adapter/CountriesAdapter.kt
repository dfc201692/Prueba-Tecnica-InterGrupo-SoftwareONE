package com.example.prueba.view.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.prueba.databinding.ItemCountryBinding
import com.example.prueba.model.models.ResponseGetCountries
import com.example.prueba.model.models.ResponseGetCountriesItem

class CountriesAdapter : RecyclerView.Adapter<CountriesAdapter.Holder>(){

    private var countriesListOriginal: List<ResponseGetCountriesItem> = emptyList()
    private var countriesListFiltered: List<ResponseGetCountriesItem> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val country = countriesListFiltered[position]
        holder.binding.tvCountryName.text = country.countryName
        holder.binding.tvCountryPhoneCode.text = "Código teléfonico; ${country.countryPhoneCode}"
        holder.binding.tvCountryShortName.text = "Nombre corto; ${country.countryShortName}"
        holder.binding.ivNavigate.setOnClickListener {
            val gmmIntentUri = Uri.parse("geo:0,0?q=${country.countryName}")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            holder.binding.root.context.startActivity(mapIntent)
        }
    }

    override fun getItemCount(): Int {
        return countriesListFiltered.size
    }

    fun submitCountryList(responseGetCountries: ResponseGetCountries) {
        countriesListOriginal = responseGetCountries
        countriesListFiltered = countriesListOriginal
        notifyDataSetChanged()
    }

    fun filterCountryList(filter: String) {
        if (filter.isNotBlank()){
            countriesListFiltered = countriesListOriginal.filter {
                it.countryName.lowercase().contains(filter.lowercase())
            }
            notifyDataSetChanged()
        }else{
            if (countriesListFiltered != countriesListOriginal) {
                countriesListFiltered = countriesListOriginal
                notifyDataSetChanged()
            }
        }
    }

    class Holder(val binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root)

}