package com.example.datastoreapp.ui

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datastoreapp.domain.GetDarkThemeValue
import com.example.datastoreapp.domain.PutDarkThemeValue
import com.example.datastoreapp.util.DARK_THEME_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val getDarkThemeValue: GetDarkThemeValue,
    private val putDarkThemeValue: PutDarkThemeValue
): ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    init {
        getDarkTheme()
    }

    fun onEvent(e: MyEvent) {
        when (e) {
            is MyEvent.SaveDarkThemeValue -> saveDarkThemeValue(state.darkThemeValue)
            is MyEvent.SelectedDarkThemeValue -> state = state.copy(darkThemeValue = e.value)
        }
    }

    private fun saveDarkThemeValue(value: Boolean) {
        viewModelScope.launch {
            putDarkThemeValue(DARK_THEME_KEY, value)
        }
    }

    private fun getDarkTheme() {
        viewModelScope.launch {
            getDarkThemeValue(DARK_THEME_KEY)?.let { state = state.copy(darkThemeValue = it) }
        }
    }
}