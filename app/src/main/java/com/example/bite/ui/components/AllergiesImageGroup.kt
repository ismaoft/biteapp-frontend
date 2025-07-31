package com.example.bite.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun AllergiesImageGroup(
    allergens: List<String>,                // Lista de URLs de las imágenes de alergias
    selectedItems: MutableState<List<String>>, // Lista mutable para los elementos seleccionados
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(top = 16.dp)) {
        for (rowIndex in 0 until 4) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                for (colIndex in 0 until 3) {
                    val index = rowIndex * 3 + colIndex
                    if (index < allergens.size) {
                        Image(
                            painter = rememberAsyncImagePainter(allergens[index]),
                            contentDescription = null,
                            modifier = Modifier
                                .size(64.dp)
                                .clickable {
                                    val currentSelection = selectedItems.value.toMutableList()
                                    if (currentSelection.contains(allergens[index])) {
                                        currentSelection.remove(allergens[index])
                                    } else {
                                        currentSelection.add(allergens[index])
                                    }
                                    selectedItems.value = currentSelection
                                }
                                .padding(8.dp),
                            contentScale = ContentScale.Fit
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
