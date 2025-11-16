package com.example.quotify.screens.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.quotify.data.Quote

class FavouriteViewModel : ViewModel(){

    private val _items = mutableStateListOf<Quote>()
    val getItems: List<Quote> = _items

    fun addItem(quote: Quote) {
        if(quote !in _items){
            _items.add(quote)
        }
    }

    fun removeItem(quote: Quote){
        if(_items.contains(quote)){
            _items.remove(quote)
        }
    }

    fun getItems(): List<Quote> = getItems
}