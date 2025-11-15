package com.example.quotify.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

enum class QuoteCategory(val displayName: String, val icon: ImageVector, val bgColor: Color) {
    LIFE("Life", Icons.Filled.Favorite, Color(0xFF1E40AF)),
    MOTIVATION("Motivation", Icons.Filled.Star, Color(0xFFD97706)),
    SUCCESS("Success", Icons.AutoMirrored.Filled.TrendingUp, Color(0xFF059669)),
    WISDOM("Wisdom", Icons.Filled.Info, Color(0xFF7C3AED)),
    LOVE("Love", Icons.Filled.FavoriteBorder, Color(0xFFDC2626)),
    COURAGE("Courage", Icons.Filled.Shield, Color(0xFF0891B2)),
    LEADERSHIP("Leadership", Icons.Filled.Groups, Color(0xFF1F2937));


    companion object {
        fun getCategory(name: String?): QuoteCategory? {
            return QuoteCategory.entries.firstOrNull {
                it.name.equals(name, ignoreCase = true)
            }
        }
    }
}