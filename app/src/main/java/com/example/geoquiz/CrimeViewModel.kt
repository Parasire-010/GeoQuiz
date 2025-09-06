package com.example.geoquiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CrimeViewModel(private val crimeRepository: CrimeRepository) : ViewModel() {

    private val _crimeList = MutableLiveData<List<Crime>>()
    val crimeList: LiveData<List<Crime>> get() = _crimeList

    init {
        loadCrimes()
    }

    private fun loadCrimes() {
        viewModelScope.launch {
            crimeRepository.crimes.collect { crimes ->
                _crimeList.value = crimes
            }
        }
    }

    fun addCrime(crime: Crime) {
        viewModelScope.launch {
            crimeRepository.addCrime(crime)
        }
    }
}
