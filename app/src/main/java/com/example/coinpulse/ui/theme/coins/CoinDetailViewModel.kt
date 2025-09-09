package com.example.coinpulse.ui.theme.coins

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinpulse.data.remote.repository.CoinRepository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CoinDetailViewModel(
    private val repository: CoinRepository // ✅ Inject repo
) : ViewModel() {

    private val _state = MutableStateFlow(CoinDetailState())
    val state: StateFlow<CoinDetailState> = _state

    fun loadCoinDetails(id: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            try {
                val coin = repository.getCoinById(id) // ✅ Fetch from API
                _state.value = CoinDetailState(coin = coin)
            } catch (e: Exception) {
                _state.value = CoinDetailState(error = e.message)
            }
        }
    }
}
