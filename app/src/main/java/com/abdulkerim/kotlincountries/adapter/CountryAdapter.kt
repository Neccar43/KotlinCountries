package com.abdulkerim.kotlincountries.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.abdulkerim.kotlincountries.R
import com.abdulkerim.kotlincountries.databinding.CountryRowBinding
import com.abdulkerim.kotlincountries.model.Country
import com.abdulkerim.kotlincountries.util.downloadFromURL
import com.abdulkerim.kotlincountries.util.placeHolderProgressBar
import com.abdulkerim.kotlincountries.view.FeedFragmentDirections
import kotlin.properties.Delegates

class CountryAdapter(private val countryList: ArrayList<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryHolder>(),CountryClickListener {
    class CountryHolder(val binding: CountryRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val binding = DataBindingUtil.inflate<CountryRowBinding>(
            LayoutInflater.from(parent.context),
            R.layout.country_row, parent, false
        )
        return CountryHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        //dataBinding
        holder.binding.selectedCountry=countryList.get(position)
        holder.binding.listener=this

        //viewBinding
       /* holder.binding.imageView.downloadFromURL(
            countryList.get(position).imageUrl,
            placeHolderProgressBar(holder.itemView.context)
        )
        holder.binding.feedNameText.text = countryList.get(position).countryName
        holder.binding.feedRegionText.text = countryList.get(position).countryRegion

        holder.binding.countryLinerLayout.setOnClickListener {
            val action =
                FeedFragmentDirections.actionFeedFragmentToCountryFragment(countryList.get(position).uid)
            it.findNavController().navigate(action)
        }*/


    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    //Kullanıcı sayfayı yenilediğinde kullanılacak fonksiyon
    @SuppressLint("NotifyDataSetChanged")
    fun updateCountryList(newCountryList: List<Country>) {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

    override fun onCountryClickListener(v: View) {
        //Bu kısım sıkıntılı
        val textView = v.findViewById<TextView>(R.id.countryUidText)
        val uid = textView.text.toString().toInt()
        val action =
            FeedFragmentDirections.actionFeedFragmentToCountryFragment(uid)
        v.findNavController().navigate(action)
    }
}