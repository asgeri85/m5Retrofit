package com.example.dersretrofitson.screen

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.dersretrofitson.databinding.FragmentWordBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WordFragment : Fragment() {
    private lateinit var binding: FragmentWordBinding
    private val wordAdapter = WordAdapter()
    private val viewModel by viewModels<WordViewModel>()

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWordBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        binding.rvWord.adapter = wordAdapter

        viewModel.getAllWord()

        val text = sharedPreferences.getString("soz", "")

        binding.textView4.text = text

        binding.button.setOnClickListener {
            /* val word = WordModel("alma", "apple")
             viewModel.addWord(word)*/

            sharedPreferences.edit().putString("soz", "Software Village").apply()
        }
    }

    private fun observeData() {
        viewModel.wordList.observe(viewLifecycleOwner) { wordList ->
            wordAdapter.updateList(wordList)
        }
    }

}