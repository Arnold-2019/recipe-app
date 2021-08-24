package com.example.myapplication.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.reflect.KFunction0

@Composable
fun FoodCategoryChip(
    category: String,
    isSelected: Boolean = false,
    onSelectedCategoryChanged: (String) -> Unit,
    onExecuteSearch: () -> Unit
) {
    Surface(
        modifier = Modifier.padding(end = 8.dp),
        elevation = 8.dp,
        shape = MaterialTheme.shapes.medium,
        color = if (isSelected) Color.LightGray else MaterialTheme.colors.primary
    ) {
        Text(
            text = category,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.secondary,
            modifier = Modifier
                .padding(8.dp)
                .clickable(
                    onClick = {
                        onSelectedCategoryChanged(category)
                        onExecuteSearch()
                    }
                )
        )
    }
}

@Preview
@Composable
fun ComposablePreview() {
    FoodCategoryChip(
        category = "Chicken",
        isSelected = true,
        onSelectedCategoryChanged = {},
        onExecuteSearch = {})
}

