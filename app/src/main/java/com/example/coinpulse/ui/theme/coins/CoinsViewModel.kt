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

    private var currentOffset = 0   // ✅ Start from 0
    private val limit = 20
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

                _coins.value = _coins.value + newCoins
                currentOffset += newCoins.size // ✅ Increase offset correctly

                // ✅ If API returns fewer than limit, we've reached the end
                if (newCoins.size < limit) allCoinsLoaded = true

            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}
