package com.example.coinpulse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.coinpulse.ui.theme.CoinPulseTheme
import com.example.coinpulse.ui.theme.coins.CoinsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoinPulseTheme {
                CoinsScreen()
            }
        }
    }
}
