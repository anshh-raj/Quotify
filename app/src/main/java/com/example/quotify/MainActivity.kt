package com.example.quotify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.quotify.models.QuoteItem
import com.example.quotify.screens.QuoteDetail
import com.example.quotify.screens.QuoteListScreen
import com.example.quotify.ui.theme.Pages
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //this will load the data on the background thread and not disturb the main thread
        CoroutineScope(Dispatchers.IO).launch {
//            delay(5000)
            DataManager.loadAssetsFromFile(applicationContext)
        }
        setContent {
            App()
        }
    }
}

@Composable
fun App(){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        if(DataManager.isDataLoaded.value){
            if(DataManager.currentPage.value == Pages.LISTING){
                QuoteListScreen(DataManager.data) {quote: QuoteItem ->
                    DataManager.switchPages(quote)

                }
            }
            else{
                DataManager.currentQuote?.let { QuoteDetail(it) }
            }
        }
        else{
            CircularProgressIndicator()
        }
    }
}