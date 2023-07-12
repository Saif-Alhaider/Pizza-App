package com.example.pizzaapp.ui.screen

import androidx.annotation.DrawableRes
import com.example.pizzaapp.R
import com.example.pizzaapp.entity.PizzaEntity
import com.example.pizzaapp.entity.PizzaSize
import com.example.pizzaapp.entity.Topping
import com.example.pizzaapp.entity.ToppingEntity

data class HomeUiState(
    val pizzas: List<PizzaUiState> = emptyList(),
    val currentPizza: Int = 0,
) {
    data class PizzaUiState(
        @DrawableRes val breadImageRes: Int = R.drawable.bread_1,
        val toppings: List<ToppingUiState> = emptyList(),
        val size: PizzaSize = PizzaSize.Medium,
    )

    data class ToppingUiState(
        @DrawableRes val singleItemImageRes: Int,
        @DrawableRes val groupImageRes: Int,
        val type: Topping,
        val isActive: Boolean = false
    )
}

fun List<PizzaEntity>.mapPizzaToUi(): List<HomeUiState.PizzaUiState> {
    return map {
        HomeUiState.PizzaUiState(
            breadImageRes = it.breadImageRes,
            toppings = it.toppings.mapToppingToUi(),
            size = it.size
        )
    }
}


fun List<ToppingEntity>.mapToppingToUi(): List<HomeUiState.ToppingUiState> {
    return map {
        HomeUiState.ToppingUiState(
            singleItemImageRes = it.singleItemImageRes,
            groupImageRes = it.groupImageRes,
            type = it.type
        )
    }
}