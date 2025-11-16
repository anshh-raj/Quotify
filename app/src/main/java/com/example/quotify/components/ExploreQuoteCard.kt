package com.example.quotify.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.quotify.data.Quote
import com.example.quotify.screens.viewModel.FavouriteViewModel

@Composable
fun ExploreQuoteCard(viewModel: FavouriteViewModel,quoteItem: Quote) {

    val itemList = viewModel.getItems()

    val liked = remember {
        mutableStateOf(itemList.contains(quoteItem))
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(border = BorderStroke(1.dp, Color.LightGray), RoundedCornerShape(10.dp))

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = CircleShape,
                        color = Color.Gray.copy(alpha = 0.2f)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Person,
                            contentDescription = "Icon",
                            modifier = Modifier.padding(8.dp),
                            tint = quoteItem.category.bgColor
                        )
                    }

                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(
                            text = quoteItem.author,
                            style = MaterialTheme.typography.labelLarge
                        )
                        Text(
                            text = "@ ${quoteItem.category.displayName}",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }

                Row{

                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(16.dp)
                            .clickable{}
                    )

                    Spacer(modifier = Modifier.padding(horizontal = 8.dp))

                    Icon(
                        imageVector = if (itemList.contains(quoteItem)) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "",
                        tint = if(itemList.contains(quoteItem)) Color.Red else Color.Black,
                        modifier = Modifier
                            .size(16.dp)
                            .clickable{
                                liked.value = !liked.value
                                if(liked.value){
                                    viewModel.addItem(quote = quoteItem)
                                }
                                else{
                                    viewModel.removeItem(quote = quoteItem)
                                }
                            }
                    )

                }
            }

            Spacer(modifier = Modifier.padding(10.dp))

            Text(
                text = quoteItem.text,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }

}