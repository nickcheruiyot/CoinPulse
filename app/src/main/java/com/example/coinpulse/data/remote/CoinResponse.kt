
package com.example.coinpulse.data.remote

data class CoinResponse(
    val status: String,
    val data: CoinData // rename from Data -> CoinData
)

data class CoinData(
    val coins: List<Coin>
)

data class Coin(
    val uuid: String,
    val name: String,
    val iconUrl: String,
    val price: String,
    val change: String
)
