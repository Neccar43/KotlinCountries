package com.abdulkerim.kotlincountries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abdulkerim.kotlincountries.model.Country
import com.abdulkerim.kotlincountries.service.CountryAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FeedViewModel: ViewModel() {
    private val disposable=CompositeDisposable()//Yaptığımız Call'ların kullanıldıktan sonra hafızada yer kaplamasını önler.(Kullan At)
    private val api=CountryAPIService()
    val countries= MutableLiveData<List<Country>>()
    val countryError= MutableLiveData<Boolean>()
    val countryLoading= MutableLiveData<Boolean>()

    fun refreshData(){
        getDataFromAPI()
    }
    private fun getDataFromAPI(){
        countryLoading.value=true
        disposable.add(
            api.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>(){
                    override fun onSuccess(t: List<Country>) {
                        countries.value=t
                        countryLoading.value=false
                        countryError.value=false
                    }

                    override fun onError(e: Throwable) {
                        countryLoading.value=false
                        countryError.value=true
                    }

                })
        )

    }
}