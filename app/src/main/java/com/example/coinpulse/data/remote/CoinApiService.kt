package com.example.coinpulse.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("coins")
    suspend fun getCoins(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0   // ✅ start at 0, not 1
    ): CoinResponse
}



