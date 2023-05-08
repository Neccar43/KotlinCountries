package com.abdulkerim.kotlincountries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.abdulkerim.kotlincountries.adapter.CountryAdapter
import com.abdulkerim.kotlincountries.databinding.FragmentFeedBinding
import com.abdulkerim.kotlincountries.viewmodel.FeedViewModel

private lateinit var binding: FragmentFeedBinding
class FeedFragment : Fragment() {
private lateinit var viewModel: FeedViewModel
private val countryAdapter=CountryAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFeedBinding.inflate(inflater,container,false)

        binding.countryRecycler.adapter=countryAdapter
        binding.countryRecycler.layoutManager=LinearLayoutManager(activity)

        viewModel=ViewModelProvider(this).get(FeedViewModel::class.java)
        viewModel.refreshData()


        observeLiveData()


        return binding.root
    }

    private fun observeLiveData(){
        viewModel.countries.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.countryRecycler.visibility=View.VISIBLE
                countryAdapter.updateCountryList(it)
            }
        })

        viewModel.countryError.observe(viewLifecycleOwner,Observer{
            it?.let {
                if (it){
                    binding.countryErrorText.visibility=View.VISIBLE
                }else{
                    binding.countryErrorText.visibility=View.GONE
                }
            }
        })

        viewModel.countryLoading.observe(viewLifecycleOwner,Observer{
            it?.let {
                if (it){
                    binding.countryProgressBar.visibility=View.VISIBLE
                    binding.countryErrorText.visibility=View.GONE
                    binding.countryRecycler.visibility=View.GONE
                }else{
                    binding.countryProgressBar.visibility=View.GONE

                }
            }
        })
    }

}