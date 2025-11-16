package com.example.quotify.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.quotify.data.Quote
import com.example.quotify.screens.viewModel.FavouriteViewModel

@Composable
fun QuotesCard(viewModel: FavouriteViewModel, quoteItem: Quote){

    val itemList = viewModel.getItems()

    val liked = remember {
        mutableStateOf(itemList.contains(quoteItem))
    }

    Card(
        modifier = Modifier
            .height(300.dp)
            .width(250.dp)
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            quoteItem.category.bgColor,
                            quoteItem.category.bgColor.copy(alpha = 0.8f),
                            quoteItem.category.bgColor.copy(alpha = 0.6f)
                        )
                    )
                )
                .padding(15.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Surface (
                    modifier = Modifier
                        .size(30.dp),
                    shape = CircleShape,
                    color = Color.White.copy(alpha = 0.5f)
                ){}

                Row{
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }

                    IconButton(
                        onClick = {
                            liked.value = !liked.value
                            if(liked.value){
                                viewModel.addItem(quote = quoteItem)
                            }
                            else{
                                viewModel.removeItem(quote = quoteItem)
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (itemList.contains(quoteItem)) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "",
                            tint = if(itemList.contains(quoteItem)) Color.Red else Color.White,
                        )
                    }
                }

            }

            Text(
                text = quoteItem.text,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )

            Text(
                text = "- ${quoteItem.author}",
                style = MaterialTheme.typography.labelLarge,
                color = Color.White
            )
        }



    }
}