package com.example.geoquiz

import kotlinx.coroutines.flow.Flow

class CrimeRepository(private val crimeDao: CrimeDao) {
    val crimes: Flow<List<Crime>> = crimeDao.getCrimes()

    suspend fun addCrime(crime: Crime) {
        crimeDao.addCrime(crime)
    }
}
