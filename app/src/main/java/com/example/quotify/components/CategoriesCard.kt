package com.example.quotify.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.quotify.data.QuoteCategory

@Composable
fun CategoriesCard(category: QuoteCategory, onNavigateToCard: (category: String) -> Unit) {

    Box(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.Gray.copy(alpha = 0.1f))
            .clickable {
                onNavigateToCard(category.displayName)
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(12.dp)
        ) {
            Surface(
                modifier = Modifier.padding(bottom = 8.dp),
                shape = CircleShape,
                color = category.bgColor.copy(alpha = 0.5f)
            ) {
                Icon(
                    imageVector = category.icon,
                    tint = category.bgColor,
                    contentDescription = "Life",
                    modifier = Modifier.padding(14.dp)
                )
            }

            Text(
                text = category.displayName,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}