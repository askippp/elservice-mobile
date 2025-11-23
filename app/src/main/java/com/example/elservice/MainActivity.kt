package com.example.elservice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.elservice.ui.navigation.AppNavHost
import com.example.elservice.ui.theme.ElserviceTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			ElserviceTheme {
				val navController = rememberNavController()

				AppNavHost(
					navController = navController,
					modifier = Modifier.fillMaxSize()
				)
			}
		}
	}
}
