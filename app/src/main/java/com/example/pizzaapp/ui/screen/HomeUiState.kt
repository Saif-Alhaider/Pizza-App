package com.example.pizzaapp.ui.screen

data class HomeUiState(
    val pizzas: List<PizzaUiState> = emptyList()
) {
    data class PizzaUiState(
        val toppings: List<Toppings> = emptyList(),
        val size: PizzaSize = PizzaSize.MEDIUM,
    )

    enum class Toppings {
        BASIL, BROCCOLI, MUSHROOM, ONION, SAUSAGE
    }

    enum class PizzaSize {
        SMALL, MEDIUM, LARGE
    }
}
