package com.mangayo.mangayo.model

class Product(var name: String, var price: Float) {

    fun getPriceString(): String{
        return "$price€"
    }
}

