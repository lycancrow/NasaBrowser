package com.example.nasabrowser.core


import com.example.nasabrowser.data.network.ApiService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class ServiceLocator() {

    //Configuration of the retrofit and the base url
    companion object {
        private const val BASE_URL = "https://images-api.nasa.gov/"
        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    val apiService: ApiService = retrofit.create(ApiService::class.java)


}
