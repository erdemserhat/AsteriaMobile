package com.asteriatech.mobile.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ThermalImageContent(matrix: List<List<Double>>,modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(2.dp)
        ) {
            repeat(matrix.size) { i ->
                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(matrix[i].size) { j ->
                        val color = getColorFromTemperature(matrix[i][j])
                        Box(
                            modifier = Modifier
                                .background(color)
                                .size(15.dp)
                                .padding(1.dp)
                        )
                    }
                }
            }
        }
    }
}