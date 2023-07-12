package com.example.pizzaapp.entity

import androidx.annotation.DrawableRes

data class PizzaEntity(
    @DrawableRes val breadImageRes: Int,
    val toppings: List<ToppingEntity> = emptyList(),
    val size: PizzaSize = PizzaSize.Medium,
)
sealed class PizzaSize(val scale:Float){
    object Small:PizzaSize(scale = .6f)
    object Medium:PizzaSize(scale = .7f)
    object Large:PizzaSize(scale = .75f)
}
//SMALL, MEDIUM, LARGE
