package com.example.pizzaapp.entity

import androidx.annotation.DrawableRes

data class ToppingEntity(
    @DrawableRes val imageRes: Int,
    val type: Topping,
)


enum class Topping {
    BASIL, BROCCOLI, MUSHROOM, ONION, SAUSAGE
}