package com.example.nasabrowser.data.repository


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nasabrowser.data.domain.Domain
import com.example.nasabrowser.data.network.ApiService
import com.example.nasabrowser.data.network.model.NasaModel
import retrofit2.HttpException
import java.io.IOException

class MainRepository(private val apiService: ApiService){

    private val _nasaImage =  MutableLiveData<List<Domain>>()
    val nasaImage : LiveData<List<Domain>> get() = _nasaImage

    suspend fun loadNasaImage(query: String){

        val result: NasaModel? = try {
            //catch the text from the textView
            apiService.getNasaInfos(query)
        } catch (e: HttpException) {
            null
        } catch (e: IOException) {
            null
        } catch (e: Exception) {
            null
        }

        result?.let {
            //sets the values do the domain
            _nasaImage.value = it.collection.items.mapNotNull {
                it.toDomain()
            }
        }?: run{
            //if the result is null, clear the view
            _nasaImage.value = _nasaImage.value?.toMutableList()?.apply {
                clear()
            }?.toList()


        }

    }

}