package com.example.quotify.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.quotify.navigation.QuotesScreen

@Composable
fun BottomNavigationBar( onClick: (route:String) -> Unit){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            IconButton(onClick = { onClick(QuotesScreen.Home.route) }) {
                Icon(
                    Icons.Filled.Home,
                    contentDescription = "Home"
                )
            }

            Text(
                text = "Home",
                style = MaterialTheme.typography.labelSmall
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            IconButton(onClick = { onClick("${QuotesScreen.Explore.route}/null") }) {
                Icon(
                    Icons.Filled.Explore,
                    contentDescription = "Explore",
                )
            }

            Text(
                text = "Explore",
                style = MaterialTheme.typography.labelSmall
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            IconButton(onClick = { onClick(QuotesScreen.Favourite.route) }) {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Favourite",
                )
            }

            Text(
                text = "Saved",
                style = MaterialTheme.typography.labelSmall
            )
        }

    }
}