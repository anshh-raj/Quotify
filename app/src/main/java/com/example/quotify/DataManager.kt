package com.example.quotify

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.example.quotify.models.QuoteItem
import com.google.gson.Gson

object DataManager {

    var data = emptyArray<QuoteItem>()
    var currentQuote: QuoteItem? = null
    var isDataLoaded = mutableStateOf(false)
    var currentPage = mutableStateOf(Pages.LISTING)

    fun loadAssetsFromFile(context: Context){

        val inputStream = context.assets.open("quotes.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        data = gson.fromJson(json, Array<QuoteItem>::class.java)
        isDataLoaded.value = true

    }

    fun switchPages(quoteItem: QuoteItem?){
        if(currentPage.value == Pages.LISTING){
            currentQuote = quoteItem
            currentPage.value = Pages.DETAIL
        }
        else{
            currentPage.value = Pages.LISTING
        }
    }
}

enum class Pages{
    LISTING,
    DETAIL
}