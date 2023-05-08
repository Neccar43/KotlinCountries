package com.abdulkerim.kotlincountries.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdulkerim.kotlincountries.databinding.CountryRowBinding
import com.abdulkerim.kotlincountries.model.Country

class CountryAdapter(private val countryList: ArrayList<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryHolder>() {

    class CountryHolder( val binding: CountryRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val binding = CountryRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
       // holder.binding.imageView
        holder.binding.feedNameText.text=countryList.get(position).countryName
        holder.binding.feedRegionText.text=countryList.get(position).countryRegion


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