package com.animalcatalog.data.api

import com.animalcatalog.data.model.CurrencyModel
import com.animalcatalog.data.retrofit.Url
import retrofit2.Response
import retrofit2.http.GET

interface ApiRequests {

    @GET(Url.REQUEST_URL)
    suspend fun getCurrency(): Response<CurrencyModel>
}