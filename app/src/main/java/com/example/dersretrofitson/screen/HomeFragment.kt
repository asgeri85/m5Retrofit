package com.example.dersretrofitson.screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.dersretrofitson.api.RetrofitClient
import com.example.dersretrofitson.databinding.FragmentHomeBinding
import com.example.dersretrofitson.model.ProductResponseModelItem
import com.example.dersretrofitson.util.goneItem
import com.example.dersretrofitson.util.visibleItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val productAdapter = ProductAdapter()
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvProducts.adapter = productAdapter

        observeData()

        binding.buttonCounter.setOnClickListener {
            viewModel.artir()
        }

        binding.buttonAzalt.setOnClickListener {
            viewModel.azalt()
        }
        // getProducts()
    }

    private fun observeData() {
        viewModel.counter.observe(viewLifecycleOwner) { counter ->
            binding.textViewCounter.text = counter.toString()
        }
    }

    private fun getProducts() {
        val api = RetrofitClient(requireContext())
        binding.progressBar.visibleItem()

        api.createApi().getAllProducts().enqueue(object : Callback<List<ProductResponseModelItem>> {
            override fun onResponse(
                call: Call<List<ProductResponseModelItem>>,
                response: Response<List<ProductResponseModelItem>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        productAdapter.updateList(it)
                    }
                }
                binding.progressBar.goneItem()
                Log.e("xeta", response.errorBody().toString())
            }

            override fun onFailure(call: Call<List<ProductResponseModelItem>>, t: Throwable) {
                Log.e("gelenXeta", t.message.toString())

            }

        })
    }

}