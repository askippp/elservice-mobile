package com.example.elservice.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.dataStore by preferencesDataStore("user_preferences")

class UserPreferences(private val context: Context) {

	private val TOKEN_KEY = stringPreferencesKey("auth_token")

	suspend fun saveToken(token: String) {
		context.dataStore.edit { it[TOKEN_KEY] = token }
	}

	suspend fun getToken(): String? =
		context.dataStore.data.first()[TOKEN_KEY]

	suspend fun clearToken() {
		context.dataStore.edit { it.remove(TOKEN_KEY) }
	}
}