package com.example.myapplication

import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel

class RecipeViewModel @ViewModelInject constructor() : ViewModel() {
    val query = mutableStateOf("")

    fun onQueryChanged(query: String) {
        this.query.value = query
    }
}
