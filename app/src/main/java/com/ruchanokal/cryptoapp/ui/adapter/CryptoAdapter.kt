package com.ruchanokal.cryptoapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ruchanokal.cryptoapp.databinding.CryptoRowBinding
import com.ruchanokal.cryptoapp.model.cyrptos.CryptoResponse
import com.ruchanokal.cryptoapp.model.cyrptos.Data
import com.ruchanokal.cryptoapp.ui.main.MainFragmentDirections

class CryptoAdapter(val cryptoList : ArrayList<Data>) : RecyclerView.Adapter<CryptoAdapter.CryptoHolder>() {

    class CryptoHolder(val binding : CryptoRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHolder {
        val binding = CryptoRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CryptoHolder(binding)
    }

    override fun onBindViewHolder(holder: CryptoHolder, position: Int) {

        holder.binding.crypto = cryptoList.get(position)

        holder.itemView.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToDetailFragment(cryptoList.get(position).symbol)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return cryptoList.size
    }

    fun refreshList(newList : List<Data>){
        cryptoList.clear()
        cryptoList.addAll(newList)
        notifyDataSetChanged()
    }

}