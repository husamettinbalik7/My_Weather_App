package com.balik.android.myweatherapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.balik.android.myweatherapp.data.local.WeatherDB
import com.balik.android.myweatherapp.databinding.FragmentHomeBinding
import com.balik.android.myweatherapp.model.WeatherResponse
import com.balik.android.myweatherapp.network.WeatherService
import com.balik.android.myweatherapp.repository.WeatherRepository
import com.balik.android.myweatherapp.util.MyViewModelFactory


class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var viewModel : HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)

        val repository = WeatherRepository(WeatherService.create(),WeatherDB.getInstance(requireContext()))
        val factory = MyViewModelFactory(repository)
        viewModel = ViewModelProvider(requireActivity(),factory)[HomeViewModel::class.java]

        viewModel.getDataFromService()

        initObserve()


        return binding.root
    }

    fun initObserve(){
        viewModel.weatherData.observe(viewLifecycleOwner){
            initRecyclerView(it)

        }
    }


    fun initRecyclerView(weatherResponse: WeatherResponse?){
        val adapter = weatherResponse?.let { HomeAdapter(it){ position ->
            val time = weatherResponse.daily.time[position]
            val maxTemp = weatherResponse.daily.apparent_temperature_max[position]
            val minTemp = weatherResponse.daily.apparent_temperature_min[position]
            Toast.makeText(requireContext(),position.toString(),Toast.LENGTH_SHORT).show()
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                time,
                maxTemp.toFloat(),
                minTemp.toFloat()
            ))
        } }
        binding.weatherRV.adapter= adapter
        binding.weatherRV.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

    }

}