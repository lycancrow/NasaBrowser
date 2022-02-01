package com.example.nasabrowser.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nasabrowser.NasaViwerApplication
import com.example.nasabrowser.R
import com.example.nasabrowser.data.domain.Domain
import com.example.nasabrowser.data.repository.MainRepository
import com.example.nasabrowser.databinding.ActivityMainBinding
import com.example.nasabrowser.keys.BundleKeys
import com.example.nasabrowser.ui.details.Detailed_Image


class MainActivity : AppCompatActivity() {

    //calls the viewModel
    private val viewModel by viewModels<MainViewModel> {
        val apiService = (application as NasaViwerApplication).serviceLocator.apiService
        val repository = MainRepository(apiService)
        MainViewModelFactory(repository)
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Executes the button
        binding.searchButton.setOnClickListener {
            viewModel.input = binding.browser.text.toString()
            viewModel.requestNasa()
        }

        //Configurates the onClick of the recyclerview
        binding.nasaRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NasaListAdapter(object : OnDetailsClickListener {
            override fun onDetailsClick(domain: Domain) {
                viewModel.onNasaClick(domain)
            }
        })


        //Executes the recyclerview
        binding.nasaRecyclerView.adapter = adapter
        viewModel.nasaInfos.observe(this) {
            adapter.submitList(it)
        }

        //Open the details view and send the data
        viewModel.navigateToDetails.observe(this){
            it?.let {
                val intent = Intent(this, Detailed_Image::class.java).apply {
                    putExtra(BundleKeys.NASA_TITLE, it.title)
                    putExtra(BundleKeys.NASA_DATE, it.date)
                    putExtra(BundleKeys.NASA_DESCRIPTION, it.description)
                    putExtra(BundleKeys.NASA_URL, it.href)

                }
                startActivity(intent)
                viewModel.onNavigateToDetailComplete()
            }
        }


    }


}