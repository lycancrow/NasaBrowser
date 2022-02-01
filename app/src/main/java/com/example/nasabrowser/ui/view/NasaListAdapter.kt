package com.example.nasabrowser.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nasabrowser.data.domain.Domain
import com.example.nasabrowser.databinding.ViewElementListBinding


//here is where the recyclerview is configured in the form of List adapter


private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Domain>() {
    override fun areItemsTheSame(oldItem: Domain, newItem: Domain): Boolean {
        return oldItem.href == newItem.href
    }
    override fun areContentsTheSame(oldItem: Domain, newItem: Domain): Boolean {
        return oldItem == newItem
    }
}

//configurates the onClick listener in the recyclerview
class NasaListAdapter(private val clickListener: OnDetailsClickListener) :
    ListAdapter<Domain, NasaViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NasaViewHolder {
        return NasaViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: NasaViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}


class NasaViewHolder private constructor(private val binding: ViewElementListBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup): NasaViewHolder {
            val binding =
                ViewElementListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return NasaViewHolder(binding)
        }
    }


    //print the inflo in the objects located in the recyclerview
    fun bind(nasaImage: Domain, clickListener: OnDetailsClickListener) {
        binding.itemTitle.text = nasaImage.title
        Glide.with(itemView).load(nasaImage.href).centerInside().into(binding.nasaImage)
        itemView.setOnClickListener {
            clickListener.onDetailsClick(nasaImage)
        }

    }

}


interface OnDetailsClickListener {
    fun onDetailsClick(domain: Domain)
}

