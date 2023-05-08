package com.abdulkerim.kotlincountries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abdulkerim.kotlincountries.model.Country

class FeedViewModel: ViewModel() {
    val countries= MutableLiveData<List<Country>>()
    val countryError= MutableLiveData<Boolean>()
    val countryLoading= MutableLiveData<Boolean>()

    fun refreshData(){
        val turkey=Country("Turkey","Asia","Ankara","TRY","Turkish",null)
        val USA=Country("USA","America","Washington DC","USD","English",null)

        countries.value= arrayListOf(turkey,USA)
        countryError.value=false
        countryLoading.value=false
    }
}