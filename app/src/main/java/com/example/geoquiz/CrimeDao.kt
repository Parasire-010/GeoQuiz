package com.example.geoquiz

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CrimeDao {
    @Query("SELECT * FROM Crime")
    fun getCrimes(): Flow<List<Crime>>

    @Insert
    suspend fun addCrime(crime: Crime)
}
