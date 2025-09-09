package com.example.coinpulse.ui.theme.coins

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FilterSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        FilterChip("Market cap", selected = true)
        FilterChip("Price")
        FilterChip("24h %")
    }
}

@Composable
fun FilterChip(text: String, selected: Boolean = false) {
    Surface(
        color = if (selected) Color.Green.copy(alpha = 0.2f) else Color.Transparent,
        shape = MaterialTheme.shapes.medium,
        tonalElevation = if (selected) 2.dp else 0.dp,
        modifier = Modifier.padding(horizontal = 4.dp)
    ) {
        Text(
            text,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            color = if (selected) Color.Green else Color.Black
        )
    }
}
