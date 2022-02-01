package com.example.nasabrowser.data.network

import com.example.nasabrowser.data.network.model.NasaModel
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("search")
    suspend fun getNasaInfos(
        @Query("q") query : String?
    ): NasaModel
}
