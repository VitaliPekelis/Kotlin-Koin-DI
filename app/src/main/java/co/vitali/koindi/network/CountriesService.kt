package co.vitali.koindi.network

import co.vitali.koindi.models.Country
import retrofit2.Response
import retrofit2.http.GET


interface CountriesService {
    @GET("all")
    suspend fun getAllCountries(): Response<List<Country>?>
}