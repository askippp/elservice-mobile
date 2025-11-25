package com.example.elservice

import android.app.Application
import com.example.elservice.core.di.AppContainer

class ElServiceApp : Application() {

	lateinit var appContainer: AppContainer
		private set

	override fun onCreate() {
		super.onCreate()
		appContainer = AppContainer(this)
	}
}
