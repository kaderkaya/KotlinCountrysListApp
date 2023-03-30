package com.kaderkayaarslan.kotlincountryslist.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kaderkayaarslan.kotlincountryslist.model.Country
import com.kaderkayaarslan.kotlincountryslist.service.CountryDatabase
import kotlinx.coroutines.launch

class CountryViewModel(application: Application): BaseViewModel(application){
    val countryLiveData = MutableLiveData<Country>()
    fun getDataFromRoom(uuid: Int) {
        launch {

           val dao = CountryDatabase(getApplication()).countryDao()
           val country = dao.getCountry(uuid)
            countryLiveData.value = country
        }

    }
}