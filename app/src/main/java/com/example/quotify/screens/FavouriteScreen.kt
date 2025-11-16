package com.example.quotify.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.quotify.components.ExploreQuoteCard
import com.example.quotify.screens.viewModel.FavouriteViewModel

@Composable
fun FavouriteScreen(viewModel: FavouriteViewModel) {

    val itemList = viewModel.getItems()

    LazyColumn(modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp)) {
        item {
            Text(
                text = "Saved",
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(8.dp),
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold

            )
        }


        items(itemList) { quote ->

            ExploreQuoteCard(viewModel, quote)

        }

    }

    if (itemList.isEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "No Saved Quotes",
                style = MaterialTheme.typography.titleMedium
            )
        }

    }


}