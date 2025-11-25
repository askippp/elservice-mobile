package com.example.elservice.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.elservice.domain.model.user.UserSession
import com.google.gson.Gson
import kotlinx.coroutines.flow.first

private val Context.dataStore by preferencesDataStore(name = "user_session")

class UserPreferences(private val context: Context) {

	private val SESSION_KEY = stringPreferencesKey("session_json")
	private val gson = Gson()

	suspend fun saveSession(session: UserSession) {
		context.dataStore.edit {
			it[SESSION_KEY] = gson.toJson(session)
		}
	}

	suspend fun getSession(): UserSession? {
		val json = context.dataStore.data.first()[SESSION_KEY] ?: return null
		return gson.fromJson(json, UserSession::class.java)
	}

	suspend fun clearSession() {
		context.dataStore.edit { it.clear() }
	}
}
