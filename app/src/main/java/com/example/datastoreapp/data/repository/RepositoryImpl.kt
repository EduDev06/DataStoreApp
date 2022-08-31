package com.example.datastoreapp.data.repository

import com.example.datastoreapp.data.source.preferences.Preferences
import com.example.datastoreapp.domain.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val preferences: Preferences
): Repository {

    override suspend fun putDarkThemeValue(key: String, value: Boolean) {
        preferences.putDarkThemeValue(key, value)
    }

    override suspend fun getDarkThemeValue(key: String): Boolean? {
        return preferences.getDarkThemeValue(key)
    }
}