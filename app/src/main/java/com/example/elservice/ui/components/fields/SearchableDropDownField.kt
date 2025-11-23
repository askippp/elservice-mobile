package com.example.elservice.ui.components.fields

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun <T> SearchableDropDownField(
	label: String,
	selectedItem: T?,
	items: List<T>,
	itemLabel: (T) -> String,
	onItemSelected: (T) -> Unit,
	modifier: Modifier = Modifier,
	prefixIcon: @Composable (() -> Unit)? = null,
	errorMessage: String? = null
) {
	var expanded by remember { mutableStateOf(false) }
	var searchQuery by remember { mutableStateOf("") }

	val filteredItems = remember(searchQuery, items) {
		if (searchQuery.isBlank()) items
		else items.filter { itemLabel(it).contains(searchQuery, ignoreCase = true) }
	}

	Column(modifier = modifier.fillMaxWidth()) {
		OutlinedTextField(
			value = selectedItem?.let(itemLabel) ?: "",
			onValueChange = {},
			readOnly = true,
			label = { Text(label) },
			leadingIcon = prefixIcon,
			trailingIcon = {
				IconButton(onClick = { expanded = !expanded }) {
					Icon(
						imageVector = if (expanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
						contentDescription = if (expanded) "Collapse" else "Expand",
						tint = if (errorMessage != null)
							MaterialTheme.colorScheme.error
						else
							MaterialTheme.colorScheme.onSurfaceVariant
					)
				}
			},
			isError = errorMessage != null,
			shape = RoundedCornerShape(12.dp),
			colors = OutlinedTextFieldDefaults.colors(
				focusedBorderColor = MaterialTheme.colorScheme.primary,
				unfocusedBorderColor = MaterialTheme.colorScheme.outline,
				errorBorderColor = MaterialTheme.colorScheme.error,
				focusedContainerColor = MaterialTheme.colorScheme.surface,
				unfocusedContainerColor = MaterialTheme.colorScheme.surface
			),
			modifier = Modifier
				.fillMaxWidth()
				.clickable(enabled = true) { expanded = true }
		)

		AnimatedVisibility(
			visible = errorMessage != null,
			enter = fadeIn() + expandVertically(),
			exit = fadeOut() + shrinkVertically()
		) {
			Row(
				modifier = Modifier.padding(start = 16.dp, top = 4.dp),
				verticalAlignment = Alignment.CenterVertically
			) {
				Icon(
					imageVector = Icons.Default.Error,
					contentDescription = null,
					tint = MaterialTheme.colorScheme.error,
					modifier = Modifier.size(16.dp)
				)
				Spacer(modifier = Modifier.width(4.dp))
				Text(
					text = errorMessage ?: "",
					color = MaterialTheme.colorScheme.error,
					style = MaterialTheme.typography.bodySmall
				)
			}
		}

		AnimatedVisibility(
			visible = expanded,
			enter = fadeIn() + expandVertically(),
			exit = fadeOut() + shrinkVertically()
		) {
			Card(
				modifier = Modifier
					.fillMaxWidth()
					.padding(top = 8.dp),
				shape = RoundedCornerShape(12.dp),
				elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
				colors = CardDefaults.cardColors(
					containerColor = MaterialTheme.colorScheme.surface
				)
			) {
				Column(
					modifier = Modifier.padding(12.dp)
				) {
					OutlinedTextField(
						value = searchQuery,
						onValueChange = { searchQuery = it },
						placeholder = {
							Text(
								"Search...",
								style = MaterialTheme.typography.bodyMedium,
								color = MaterialTheme.colorScheme.onSurfaceVariant
							)
						},
						leadingIcon = {
							Icon(
								Icons.Default.Search,
								contentDescription = "Search",
								tint = MaterialTheme.colorScheme.primary
							)
						},
						trailingIcon = {
							if (searchQuery.isNotEmpty()) {
								IconButton(onClick = { searchQuery = "" }) {
									Icon(
										Icons.Default.Clear,
										contentDescription = "Clear search",
										tint = MaterialTheme.colorScheme.onSurfaceVariant
									)
								}
							}
						},
						singleLine = true,
						shape = RoundedCornerShape(8.dp),
						colors = OutlinedTextFieldDefaults.colors(
							focusedBorderColor = MaterialTheme.colorScheme.primary,
							unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
							focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
							unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
						),
						modifier = Modifier.fillMaxWidth()
					)

					Spacer(modifier = Modifier.height(8.dp))

					HorizontalDivider(
						thickness = 1.dp,
						color = MaterialTheme.colorScheme.outlineVariant
					)

					Spacer(modifier = Modifier.height(4.dp))

					if (filteredItems.isEmpty()) {
						Box(
							modifier = Modifier
								.fillMaxWidth()
								.padding(vertical = 24.dp),
							contentAlignment = Alignment.Center
						) {
							Text(
								text = "No items found",
								style = MaterialTheme.typography.bodyMedium,
								color = MaterialTheme.colorScheme.onSurfaceVariant
							)
						}
					} else {
						LazyColumn(
							modifier = Modifier
								.fillMaxWidth()
								.heightIn(max = 250.dp)
						) {
							items(filteredItems) { item ->
								val isSelected = selectedItem == item

								Surface(
									modifier = Modifier
										.fillMaxWidth()
										.clickable {
											onItemSelected(item)
											expanded = false
											searchQuery = ""
										},
									color = if (isSelected)
										MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
									else
										Color.Transparent
								) {
									Row(
										modifier = Modifier
											.fillMaxWidth()
											.padding(horizontal = 12.dp, vertical = 14.dp),
										verticalAlignment = Alignment.CenterVertically
									) {
										Text(
											text = itemLabel(item),
											style = MaterialTheme.typography.bodyLarge,
											color = if (isSelected)
												MaterialTheme.colorScheme.primary
											else
												MaterialTheme.colorScheme.onSurface,
											fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal,
											modifier = Modifier.weight(1f)
										)

										if (isSelected) {
											Icon(
												imageVector = Icons.Default.Check,
												contentDescription = "Selected",
												tint = MaterialTheme.colorScheme.primary,
												modifier = Modifier.size(20.dp)
											)
										}
									}
								}

								if (item != filteredItems.last()) {
									HorizontalDivider(
										thickness = 0.5.dp,
										color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
									)
								}
							}
						}
					}
				}
			}
		}
	}
}
