package com.abdulkerim.kotlincountries.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.abdulkerim.kotlincountries.model.Country
import com.abdulkerim.kotlincountries.service.CountryAPIService
import com.abdulkerim.kotlincountries.service.CountryDatabase
import com.abdulkerim.kotlincountries.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FeedViewModel(application: Application) : BaseViewModel(application) {
    private val disposable =
        CompositeDisposable()//Yaptığımız Call'ların kullanıldıktan sonra hafızada yer kaplamasını önler.(Kullan At)
    private val api = CountryAPIService()
    private var customSharedPreferences = CustomSharedPreferences(getApplication())

    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    private var refreshTime = 10 * 60 * 1000 * 1000 * 1000L

    fun refreshData() {
        val updateTime = customSharedPreferences.getTime()
        //10 dakikadan küçükse SQLite dan değilse API den verileri getiriyor.
        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime) {
            getDataFromSQLite()
        } else {
            getDataFromAPI()
        }
    }

    private fun getDataFromSQLite() {
        countryLoading.value=true
        launch {
          val  countries=CountryDatabase(getApplication()).countryDao().getAllCountries()
            showCountries(countries)
            Toast.makeText(getApplication(), "Countries from SQLite", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getDataFromAPI() {
        countryLoading.value = true
        disposable.add(
            api.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>() {
                    override fun onSuccess(t: List<Country>) {
                        storeInSQLite(t)
                        Toast.makeText(getApplication(), "Countries from API", Toast.LENGTH_SHORT).show()

                    }

                    override fun onError(e: Throwable) {
                        countryLoading.value = false
                        countryError.value = true
                    }

                })
        )

    }

    private fun showCountries(countryList: List<Country>) {
        countries.value = countryList
        countryLoading.value = false
        countryError.value = false
    }

    private fun storeInSQLite(list: List<Country>) {
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            dao.deleteAllCountries()
            val longList = dao.insertAll(*list.toTypedArray())
            var i = 0
            while (i < list.size) {
                list[i].uid = longList[i].toInt()
                i++
            }
            showCountries(list)
        }
        customSharedPreferences.saveTime(System.nanoTime())

    }

    fun refreshFromAPI() {
        getDataFromAPI()
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}