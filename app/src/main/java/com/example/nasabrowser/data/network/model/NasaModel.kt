package com.example.nasabrowser.data.network.model

import android.util.Log
import com.example.nasabrowser.data.domain.Domain
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//this is the path that the applications has to follow to catch the data comming from the API
@JsonClass(generateAdapter = true)
data class NasaModel(
    val collection: Collection
)

@JsonClass(generateAdapter = true)
data class Collection(
    val href: String,
    val items: List<Elements>
)

@JsonClass(generateAdapter = true)
data class Elements(
    val data: List<NasaDetails>,
    val links: List<NasaImage?>?
) {
    fun toDomain(): Domain? {
        val detail = data.getOrNull(0) ?: return null
        Log.i("outputData",data.toString())
        val link = links?.getOrNull(0) ?: return null
        Log.i("outputTitle",detail.title)


        return Domain(detail.title, detail.date,detail.description, link.href)

    }
}



@JsonClass(generateAdapter = true)
data class NasaDetails(
    val title: String,
    @Json(name = "date_created")
    val date: String,
    val description: String
)

@JsonClass(generateAdapter = true)
data class NasaImage(
    val href: String
)
