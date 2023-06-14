package id.ac.unpas.tubesppmkelompok6kelasd.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen() {
    Column(horizontalAlignment = Alignment.Start) {
        Text(text = "Kelompok 6")
        Text(text = "Rayhan Abduhuda - 193040044", modifier = Modifier.align(Alignment.Start))
        Text(text = "Rizal Muhammad Farabi", modifier = Modifier.align(Alignment.Start))
    }
}