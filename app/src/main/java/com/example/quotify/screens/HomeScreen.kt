package com.example.quotify.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.quotify.components.BannerPage
import com.example.quotify.components.CategoriesCard
import com.example.quotify.components.QuotesCard
import com.example.quotify.components.SectionHeader
import com.example.quotify.data.Quote
import com.example.quotify.data.QuoteCategory

@Composable
fun QuoteListScreen(
    data: List<Quote>,
    onNavigateToExplore: (category: String?) -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
//            .background(MaterialTheme.colorScheme.background)
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        content = {
            item {
                Text(
                    "Explore",
                    modifier = Modifier
                        .fillMaxWidth(1f),
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Bold
//                    fontFamily = FontFamily(Font(R.font.montserrat_regular))
                )
                Text(
                    "Awesome quotes from our community",
                    modifier = Modifier
                        .fillMaxWidth(1f),
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {

                BannerPage()
            }

            item {
                SectionHeader("Latest Quotes"){
                    onNavigateToExplore(null)
                }
            }

            item {
                LazyRow{
                    items(data){
                        QuotesCard(it)
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            item{
                SectionHeader("Categories"){
                    onNavigateToExplore(null)
                }
            }

            item {
                LazyRow{
                    items(QuoteCategory.entries.toTypedArray()){
                        CategoriesCard(it){category ->
                            onNavigateToExplore(category)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            item{
                SectionHeader("Trending Quotes"){
                    onNavigateToExplore(null)
                }
            }

            item{
                LazyRow{
                    items(data){
                        QuotesCard(it)
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    )
}

