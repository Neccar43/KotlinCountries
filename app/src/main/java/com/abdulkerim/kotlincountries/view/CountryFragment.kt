package com.abdulkerim.kotlincountries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.abdulkerim.kotlincountries.databinding.FragmentCountryBinding
import com.abdulkerim.kotlincountries.util.downloadFromURL
import com.abdulkerim.kotlincountries.util.placeHolderProgressBar
import com.abdulkerim.kotlincountries.viewmodel.CountryViewModel

private lateinit var binding: FragmentCountryBinding
class CountryFragment : Fragment() {
    private val args: CountryFragmentArgs by navArgs()
    private lateinit var viewModel: CountryViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryBinding.inflate(inflater,container,false)

        val countryId=args.countryUid
        viewModel=ViewModelProvider(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom(countryId)

        observeLiveData()


        return binding.root
    }

    private fun observeLiveData(){
        viewModel.country.observe(viewLifecycleOwner,Observer{
            it?.let {
                binding.countryNameText.text=it.countryName
                binding.countryCapitalText.text=it.countryCapital
                binding.countryCurrencyText.text=it.countryCurrency
                binding.countryRegionText.text=it.countryRegion
                binding.countryLanguageText.text=it.countryLanguage
                context?.let {it2->
                    binding.countryImage.downloadFromURL(it.imageUrl, placeHolderProgressBar(it2))
                }

            }
        })
    }


}