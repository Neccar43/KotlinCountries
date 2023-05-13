package com.abdulkerim.kotlincountries.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.abdulkerim.kotlincountries.databinding.CountryRowBinding
import com.abdulkerim.kotlincountries.model.Country
import com.abdulkerim.kotlincountries.util.downloadFromURL
import com.abdulkerim.kotlincountries.util.placeHolderProgressBar
import com.abdulkerim.kotlincountries.view.FeedFragmentDirections

class CountryAdapter(private val countryList: ArrayList<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryHolder>() {

    class CountryHolder( val binding: CountryRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val binding = CountryRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        holder.binding.imageView.downloadFromURL(countryList.get(position).imageUrl,
            placeHolderProgressBar(holder.itemView.context)
        )
        holder.binding.feedNameText.text=countryList.get(position).countryName
        holder.binding.feedRegionText.text=countryList.get(position).countryRegion

        holder.binding.countryLinerLayout.setOnClickListener {
            val action=FeedFragmentDirections.actionFeedFragmentToCountryFragment(countryList.get(position).uid)
            it.findNavController().navigate(action)
        }


    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    //Kullanıcı sayfayı yenilediğinde kullanılacak fonksiyon
    @SuppressLint("NotifyDataSetChanged")
    fun updateCountryList(newCountryList: List<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }
}