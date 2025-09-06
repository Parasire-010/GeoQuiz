package com.example.geoquiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geoquiz.databinding.FragmentCrimeListBinding

class CrimeListFragment : Fragment() {

    private val crimeViewModel: CrimeViewModel by viewModels()
    private var _binding: FragmentCrimeListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CrimeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCrimeListBinding.inflate(inflater, container, false)

        adapter = CrimeAdapter(emptyList())
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@CrimeListFragment.adapter
        }

        crimeViewModel.crimeList.observe(viewLifecycleOwner) { crimes ->
            adapter.updateCrimes(crimes)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
