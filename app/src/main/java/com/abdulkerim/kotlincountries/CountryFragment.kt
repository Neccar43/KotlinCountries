package com.abdulkerim.kotlincountries

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abdulkerim.kotlincountries.databinding.FragmentCountryBinding

private lateinit var binding: FragmentCountryBinding
class CountryFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentCountryBinding.inflate(inflater,container,false)






        return binding.root
    }


}