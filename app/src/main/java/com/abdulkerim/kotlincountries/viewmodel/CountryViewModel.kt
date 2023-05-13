package com.abdulkerim.kotlincountries.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abdulkerim.kotlincountries.model.Country
import com.abdulkerim.kotlincountries.service.CountryDatabase
import kotlinx.coroutines.launch

class CountryViewModel(application: Application): BaseViewModel(application) {
    val country= MutableLiveData<Country>()

    fun getDataFromRoom(uid:Int){
        launch {
            val db=CountryDatabase(getApplication()).countryDao()
            country.value=db.getCountry(uid)

        }

    }
}