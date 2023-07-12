package com.example.pizzaapp.entity

import androidx.annotation.DrawableRes

data class ToppingEntity(
    @DrawableRes val singleItemImageRes: Int,
    @DrawableRes val groupImageRes:Int,
    val type: Topping,
)


enum class Topping {
    BASIL, BROCCOLI, MUSHROOM, ONION, SAUSAGE
}