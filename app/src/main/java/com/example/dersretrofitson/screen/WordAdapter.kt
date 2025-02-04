package com.example.dersretrofitson.screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dersretrofitson.databinding.ItemWordBinding
import com.example.dersretrofitson.model.WordModel

class WordAdapter : RecyclerView.Adapter<WordAdapter.WordViewHolder>() {

    private var wordList = arrayListOf<WordModel>()

    inner class WordViewHolder(val itemWordBinding: ItemWordBinding) :
        RecyclerView.ViewHolder(itemWordBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val layout = ItemWordBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return WordViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return wordList.size
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val word = wordList[position]

        holder.itemWordBinding.wordModel = word
    }

    fun updateList(newList: List<WordModel>) {
        wordList.clear()
        wordList.addAll(newList)
        notifyDataSetChanged()
    }
}