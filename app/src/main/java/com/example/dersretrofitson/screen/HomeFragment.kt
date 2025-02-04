package com.example.dersretrofitson.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.dersretrofitson.databinding.FragmentHomeBinding
import com.example.dersretrofitson.model.Musiqici
import com.example.dersretrofitson.util.goneItem
import com.example.dersretrofitson.util.visibleItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val productAdapter = ProductAdapter()
    private val viewModel by viewModels<HomeViewModel>()
    private val categoryAdapter = CategoryAdapter()

    @Inject
    lateinit var musiqici: Musiqici

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
        binding.rvCategory.adapter = categoryAdapter

        productAdapter.updateList(listOf())
        lifecycleScope.launch {
            productAdapter.fetch()
        }


        /* val mahni = Mahni()
         val alet = Alet()

         val musiqici = Musiqici(mahni, alet)

         musiqici.mahniOxu()*/

        musiqici.mahniOxu()

        observeData()

        categoryAdapter.onClickItem = { name ->
            viewModel.getCategoryProduct(name)
        }

        binding.buttonExit.setOnClickListener {
            viewModel.logoutFirebase()
        }

    }

    private fun observeData() {
        viewModel.productList.observe(viewLifecycleOwner) { data ->
            productAdapter.updateList(data)
        }
        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            if (loading) binding.progressBar.visibleItem() else binding.progressBar.goneItem()
        }
        viewModel.error.observe(viewLifecycleOwner) { error ->
            if (error.isNotEmpty()) {
                Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            }
        }
        viewModel.categoryList.observe(viewLifecycleOwner) { categoryData ->
            categoryAdapter.updateList(categoryData)
        }
    }

    /*  private fun getProducts() {
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
      }*/

}