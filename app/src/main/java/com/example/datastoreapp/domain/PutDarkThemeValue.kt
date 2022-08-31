package com.example.datastoreapp.domain

import javax.inject.Inject

class PutDarkThemeValue @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(key: String, value: Boolean) {
        repository.putDarkThemeValue(key, value)
    }
}