package com.animalcatalog.data.retrofit

import com.animalcatalog.data.api.ApiRequests
import com.animalcatalog.data.model.CurrencyModel
import kotlinx.coroutines.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitCreator {

    var responseText: String? = null

    suspend fun getCurrentData() = withContext(Dispatchers.IO) {

        val api = Retrofit.Builder()
            .baseUrl(Url.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequests::class.java)

            val response: Response<CurrencyModel> = api.getCurrency()
            if (response.isSuccessful) {
                val data: CurrencyModel? = response.body()!!
                responseText = data?.insult
            }
        return@withContext responseText
    }
}