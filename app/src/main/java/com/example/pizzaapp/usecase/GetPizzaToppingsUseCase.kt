package com.example.pizzaapp.usecase

import com.example.pizzaapp.data.PizzaDataSource
import javax.inject.Inject

class GetPizzaToppingsUseCase @Inject constructor(private val pizzaDataSource: PizzaDataSource) {
    operator fun invoke() = pizzaDataSource.toppings
}