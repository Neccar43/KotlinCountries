package com.abdulkerim.kotlincountries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.abdulkerim.kotlincountries.databinding.FragmentCountryBinding

private lateinit var binding: FragmentCountryBinding
class CountryFragment : Fragment() {
    private val args: CountryFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryBinding.inflate(inflater,container,false)

        val countryId=args.countryUid




        return binding.root
    }


}