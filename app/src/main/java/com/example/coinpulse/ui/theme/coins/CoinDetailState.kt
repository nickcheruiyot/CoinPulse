package com.example.coinpulse.ui.theme.coins

import com.example.coinpulse.data.remote.Coin

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: Coin? = null,
    val error: String? = null
)

