package com.example.datastoreapp.ui

sealed class MyEvent {
    data class SaveDarkThemeValue(val value: Boolean?): MyEvent()
    data class SelectedDarkThemeValue(val value: Boolean): MyEvent()
}
