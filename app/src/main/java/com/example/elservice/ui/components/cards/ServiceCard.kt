package com.example.elservice.ui.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.elservice.ui.components.texts.BodyText
import com.example.elservice.ui.components.texts.CaptionText
import com.example.elservice.ui.components.texts.LabelText
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun ServiceCard(
	namaAlat: List<String>? = null,
	tanggalMasuk: Date? = null,
	tanggalSelesai: Date? = null,
	status: String? = null,
	totalBiaya: Float? = null,
	onClick: () -> Unit = {}
) {
	val dateFormatter = SimpleDateFormat("dd MMM yyyy", Locale("id", "ID"))

	Card(
		onClick = onClick,
		shape = RoundedCornerShape(16.dp),
		colors = CardDefaults.cardColors(
			containerColor = MaterialTheme.colorScheme.surface
		),
		modifier = Modifier
			.fillMaxWidth()
			.padding(vertical = 4.dp)
	) {
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.padding(8.dp)
		) {
			Row(
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.SpaceBetween,
				modifier = Modifier.fillMaxWidth()
			) {
				Row(
					horizontalArrangement = Arrangement.spacedBy(8.dp),
					verticalAlignment = Alignment.CenterVertically
				) {
					Icon(
						imageVector = Icons.Default.CalendarMonth,
						contentDescription = null,
						tint = MaterialTheme.colorScheme.primary,
						modifier = Modifier.size(16.dp)
					)

					CaptionText(tanggalMasuk?.let { dateFormatter.format(it) } ?: "-")
				}
				
				StatusChip(status)
			}
			
			Spacer(modifier = Modifier.height(8.dp))

			HorizontalDivider(
				thickness = 2.dp,
				color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
			)

			Spacer(modifier = Modifier.height(8.dp))

			LabelText(namaAlat?.joinToString(", ") ?: "-")

			Spacer(modifier = Modifier.height(16.dp))

			Row(
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.SpaceBetween,
				modifier = Modifier.fillMaxWidth()
			) {
				Column(
					verticalArrangement = Arrangement.Center,
					horizontalAlignment = Alignment.CenterHorizontally
				) {
					CaptionText(text = "Completed on")

					Spacer(modifier = Modifier.height(4.dp))

					LabelText(tanggalMasuk?.let { dateFormatter.format(it) } ?: "-")
				}

				Column(
					verticalArrangement = Arrangement.Center,
					horizontalAlignment = Alignment.CenterHorizontally
				) {
					CaptionText(text = "Total Cost")

					Spacer(modifier = Modifier.height(4.dp))

					LabelText("Rp ${totalBiaya?.toInt() ?: "Rp 0"}")
				}
			}
		}
	}
}

@Composable
fun StatusChip(status: String?) {
	val translatedStatus = when (status?.lowercase()) {
		"menunggu" -> "Waiting"
		"dalam_proses" -> "In Progress"
		"selesai" -> "Completed"
		"batal" -> "Cancelled"
		else -> "Unknown"
	}

	val backgroundColor = when (status?.lowercase()) {
		"menunggu" -> MaterialTheme.colorScheme.tertiary
		"dalam_proses" -> MaterialTheme.colorScheme.primary
		"selesai" -> MaterialTheme.colorScheme.secondary
		"batal" -> MaterialTheme.colorScheme.error
		else -> MaterialTheme.colorScheme.outline
	}

	Box(
		modifier = Modifier
			.background(backgroundColor.copy(alpha = 0.12f), RoundedCornerShape(8.dp))
			.padding(horizontal = 8.dp, vertical = 8.dp)
	) {
		CaptionText(translatedStatus)
	}
}