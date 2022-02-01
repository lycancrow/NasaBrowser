package com.example.nasabrowser.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.nasabrowser.R
import com.example.nasabrowser.databinding.ActivityDetailedImageBinding
import com.example.nasabrowser.keys.BundleKeys

class Detailed_Image : AppCompatActivity() {

    private lateinit var binding: ActivityDetailedImageBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailedImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageTitle.text = intent.getStringExtra(BundleKeys.NASA_TITLE)
        Glide.with(this).load(intent.getStringExtra(BundleKeys.NASA_URL)).into(binding.nasaImageDetailed)
        binding.imageDescription.text = intent.getStringExtra(BundleKeys.NASA_DESCRIPTION)
        binding.imageDate.text = intent.getStringExtra(BundleKeys.NASA_DATE)



    }




}