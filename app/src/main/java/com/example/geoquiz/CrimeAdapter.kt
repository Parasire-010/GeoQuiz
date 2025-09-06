package com.example.geoquiz

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.geoquiz.databinding.ListItemCrimeBinding

class CrimeAdapter(private var crimes: List<Crime>) :
    RecyclerView.Adapter<CrimeAdapter.CrimeViewHolder>() {

    inner class CrimeViewHolder(private val binding: ListItemCrimeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(crime: Crime) {
            binding.apply {
                crimeTitleText.text = crime.title
                // Add more binding logic if needed, e.g., date or isSolved
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
        return CrimeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CrimeViewHolder, position: Int) {
        val crime = crimes[position]
        holder.bind(crime)
    }

    override fun getItemCount(): Int = crimes.size

    // Updates the data in the adapter when LiveData changes
    fun updateCrimes(newCrimes: List<Crime>) {
        crimes = newCrimes
        notifyDataSetChanged()
    }
}
