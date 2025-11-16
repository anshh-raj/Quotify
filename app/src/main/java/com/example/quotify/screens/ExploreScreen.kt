package com.example.quotify.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.quotify.components.ExploreQuoteCard
import com.example.quotify.data.Quote
import com.example.quotify.data.QuoteCategory

@Composable
fun ExploreScreen(initialCategoryName: String? = null){

    val categories: List<QuoteCategory?> = listOf(null) + QuoteCategory.entries.toTypedArray()

    val initialCategory = QuoteCategory.getCategory(initialCategoryName)
    val initialIndex = categories.indexOf(initialCategory).takeIf { it>=0 } ?: 0
    var selectedIndex by remember {
        mutableIntStateOf(initialIndex)
    }
    LazyColumn(modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp)) {
        item {
            Text(
                text = "Categories",
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(8.dp),
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold

            )
        }

        item {
            ScrollableTabRow(
                selectedTabIndex = selectedIndex,
                divider = {},
                indicator = {},
                edgePadding = 0.dp
            ) {
                categories.forEachIndexed { index, category ->

                    val isSelected = index == selectedIndex
                    val bg = if (isSelected)category?.bgColor ?: Color.Blue.copy(alpha = 0.5f)
                    else Color.Gray.copy(alpha = 0.2f)

                    Tab(
                        selected = isSelected,
                        onClick = {selectedIndex = index},
                        modifier = Modifier
                            .padding(8.dp)
                            .background(bg, RoundedCornerShape(40))

                    ) {

                        Text(
                            text = category?.displayName ?: "All",
                            color = if(isSelected) Color.White else Color.Black,
                            modifier = Modifier.padding(12.dp)
                        )

                    }
                }
            }
        }

        val selectedCategory: QuoteCategory? = categories.getOrNull(selectedIndex)
        val filteredQuotes = Quote.getQuotes().filter { quote ->
            selectedCategory == null || quote.category == selectedCategory
        }

        items(filteredQuotes){quote->

            ExploreQuoteCard(quote)

        }

    }
}