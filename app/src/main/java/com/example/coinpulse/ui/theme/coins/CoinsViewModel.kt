package com.example.coinpulse.ui.theme.coins

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinpulse.data.remote.ApiClient
import com.example.coinpulse.data.remote.Coin
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CoinsViewModel : ViewModel() {

    private val _coins = MutableStateFlow<List<Coin>>(emptyList())
    val coins: StateFlow<List<Coin>> = _coins.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private var currentOffset = 0
    private val limit = 20
    private val maxCoins = 100   // ✅ stop at 100
    private var allCoinsLoaded = false

    init {
        loadCoins()
    }

    fun loadCoins() {
        if (_isLoading.value || allCoinsLoaded) return

        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = ApiClient.api.getCoins(limit, currentOffset)
                val newCoins = response.data.coins

                // ✅ Append new coins
                _coins.value = _coins.value + newCoins

                // ✅ Move offset forward
                currentOffset += newCoins.size

                // ✅ Stop at 100 or if fewer than `limit` returned
                if (currentOffset >= maxCoins || newCoins.size < limit) {
                    allCoinsLoaded = true
                }

                println("Loaded ${newCoins.size} coins, total=${_coins.value.size}, offset=$currentOffset")

            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}

