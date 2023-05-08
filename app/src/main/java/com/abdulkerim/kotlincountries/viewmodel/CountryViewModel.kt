package com.abdulkerim.kotlincountries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abdulkerim.kotlincountries.model.Country

class CountryViewModel: ViewModel() {
    val country= MutableLiveData<Country>()

    fun getDataFromRoom(){
        country.value=Country("Turkey","Asia","Ankara","TRY","Turkish",null)
    }
}