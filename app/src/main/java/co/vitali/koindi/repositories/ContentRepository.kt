package co.vitali.koindi.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.vitali.koindi.db.CountriesDB
import co.vitali.koindi.db.CountryEntity
import co.vitali.koindi.models.Country
import co.vitali.koindi.network.CountriesService
import co.vitali.koindi.removeDuplicates
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import retrofit2.Response

class ContentRepository constructor(private val countriesService: CountriesService, private val countriesDb: CountriesDB) {

    private val mErrorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> = mErrorLiveData

    suspend fun fetchAllCountries(): Response<List<Country>?> {
        return countriesService.getAllCountries()
    }

    suspend fun getCountriesFromDb(): List<Country> {
        val result = mutableListOf<Country>()
        countriesDb.countriesDao().getCountries()?.forEach {
            result.add(it.toCountry())
        }

        return result
    }

    fun saveCountry(country: Country) {
        CoroutineScope(IO).launch {
            countriesDb.countriesDao().insertCountry(CountryEntity.from(country))
        }
    }

    suspend fun getAllCountries(): MutableList<Country> {
        var result = mutableListOf<Country>()

        val countriesFromDb = getCountriesFromDb()
        if(countriesFromDb.isNotEmpty()) {
            result.addAll(0, countriesFromDb)
        }

        val response = fetchAllCountries()
        if (response.isSuccessful) {
            val countries = response.body() as MutableList<Country>
            result.addAll(countries)
            result = removeDuplicates(result)
        } else {
            mErrorLiveData.postValue("Error : ${response.message()} ")
        }

        return result
    }
}