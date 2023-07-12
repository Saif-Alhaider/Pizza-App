package com.example.pizzaapp.usecase

import com.example.pizzaapp.data.PizzaDataSource
import javax.inject.Inject

class GetPizzasUseCase @Inject constructor(private val pizzaDataSource: PizzaDataSource) {
    operator fun invoke() = pizzaDataSource.pizzas
}