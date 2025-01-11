package com.example.dersretrofitson.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.dersretrofitson.R
import com.example.dersretrofitson.api.RetrofitClient
import com.example.dersretrofitson.databinding.FragmentDetailBinding
import com.example.dersretrofitson.model.ProductResponseModelItem
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailFragment : Fragment() {

    lateinit var binding: FragmentDetailBinding



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



      /*  binding.textViewTitle.text = model.title

        binding.textViewRating.text = model. */


    }

    private fun getProductDetail() {
        /*  api.getProductDetail(args.id).enqueue(object : Callback<ProductResponseModelItem> {
              override fun onResponse(
                  call: Call<ProductResponseModelItem>,
                  response: Response<ProductResponseModelItem>
              ) {
                  if (response.isSuccessful) {
                      response.body()?.let {
                          binding.textViewTitle.text = it.title
                          binding.textViewDescription.text = it.description
                          binding.textViewRating.text = it.rating?.rate.toString()
                          Picasso.get().load(it.image).into(binding.imageView2)
                      }
                  }
              }

              override fun onFailure(call: Call<ProductResponseModelItem>, t: Throwable) {

              }

          })*/
    }

}