package com.example.nasabrowser.ui.view

import androidx.lifecycle.*
import com.example.nasabrowser.data.domain.Domain
import com.example.nasabrowser.data.repository.MainRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException


class MainViewModel (private val repository: MainRepository) : ViewModel() {


    //https://images-api.nasa.gov/search?q={input}
    var input: String = "mars"
    val nasaInfos: LiveData<List<Domain>> = repository.nasaImage
    private val _navigateToDetails = MutableLiveData<Domain?>()
    val navigateToDetails: LiveData<Domain?> get() = _navigateToDetails

    init {
        requestNasa()
    }

    //send the value captured from the edit text to the interface
    fun requestNasa() {
        viewModelScope.launch {
            repository.loadNasaImage(input)
        }
    }

    //delegates the onclick to the element
    fun onNasaClick(nasaImage: Domain) {
        _navigateToDetails.value = nasaImage
    }


    fun onNavigateToDetailComplete() {
        _navigateToDetails.value = null

    }

}


class MainViewModelFactory(private val repository: MainRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }

        throw IllegalArgumentException("Invalid ViewModel")
    }

}