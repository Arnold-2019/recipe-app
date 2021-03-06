package com.example.myapplication.presentation.ui.recipelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myapplication.presentation.components.FoodCategoryChip
import com.example.myapplication.presentation.ui.RecipeCard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    private val viewModel: RecipeListViewModel by viewModels()

    @ExperimentalComposeUiApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val recipes = viewModel.recipes.value
                val query = viewModel.query.value
                val keyboardController = LocalSoftwareKeyboardController.current
                val selectedCategory = viewModel.selectedCategory.value

                Column {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White,
                        elevation = 8.dp
                    ) {
                        Column(modifier = Modifier.padding(8.dp)) {
                            TextField(
                                modifier = Modifier
                                    .fillMaxWidth(0.9f)
                                    .padding(bottom = 8.dp),
                                label = { Text("Search") },
                                value = query, onValueChange = viewModel::onQueryChanged,
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Done,
                                ),
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        viewModel.newSearch()
                                        keyboardController?.hide()
                                    }
                                ),
                                leadingIcon = {
                                    Icon(Icons.Filled.Search, contentDescription = "")
                                },
                                textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = MaterialTheme.colors.surface
                                )
                            )
                            LazyRow {
                                items(getAllFoodCategories()) { category ->
                                    FoodCategoryChip(
                                        category = category.value,
                                        isSelected = category == selectedCategory,
                                        onSelectedCategoryChanged = {
                                            viewModel.onSelectedCategoryChanged(it)
                                        },
                                        onExecuteSearch = viewModel::newSearch
                                    )
                                }
                            }
                        }
                    }

                    LazyColumn {
                        itemsIndexed(
                            items = recipes
                        ) { _, recipe ->
                            RecipeCard(recipe = recipe, onclick = {})
                        }
                    }
                }
            }
        }
    }
}
