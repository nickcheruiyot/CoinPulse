package com.example.coinpulse.data.remote.repository

import com.example.coinpulse.data.remote.ApiClient
import com.example.coinpulse.data.remote.Coin

class CoinRepository {
    private val api = ApiClient.api

    suspend fun getCoins(limit: Int, offset: Int): List<Coin> {
        return api.getCoins(limit, offset).data.coins
    }
}