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

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        loadCoins()
    }

    fun loadCoins() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = ApiClient.api.getCoins(limit = 20, offset = 1)
                _coins.value = response.data.coins
            } catch (e: Exception) {
                e.printStackTrace()
                _error.value = "Failed to load coins: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
