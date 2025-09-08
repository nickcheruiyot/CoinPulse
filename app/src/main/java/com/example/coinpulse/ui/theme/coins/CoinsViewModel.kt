package com.example.coinpulse.ui.theme.coins

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinpulse.data.remote.ApiClient
import com.example.coinpulse.data.remote.Coin
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CoinsViewModel : ViewModel() {

    private val _coins = MutableStateFlow<List<Coin>>(emptyList())
    val coins: StateFlow<List<Coin>> = _coins

    private var currentOffset = 1
    private val limit = 20
    var isLoading = false
        private set
    var endReached = false
        private set

    init {
        loadCoins()
    }

    fun loadCoins() {
        if (isLoading || endReached) return

        viewModelScope.launch {
            isLoading = true
            try {
                val response = ApiClient.api.getCoins(limit, currentOffset)
                val newCoins = response.data.coins
                if (newCoins.isEmpty()) {
                    endReached = true
                } else {
                    _coins.value = _coins.value + newCoins
                    currentOffset += newCoins.size
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }
}
