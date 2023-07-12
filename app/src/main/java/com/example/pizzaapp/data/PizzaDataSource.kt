package com.example.pizzaapp.data

import com.example.pizzaapp.R
import com.example.pizzaapp.di.ResourcesProvider
import com.example.pizzaapp.entity.PizzaEntity
import com.example.pizzaapp.entity.Topping
import com.example.pizzaapp.entity.ToppingEntity
import javax.inject.Inject

class PizzaDataSource @Inject constructor(private val resourcesProvider: ResourcesProvider) {
    val toppings =
        listOf(
            ToppingEntity(
                imageRes = resourcesProvider.getDrawable(R.drawable.basil_3),
                type = Topping.BASIL,
            ),
            ToppingEntity(
                imageRes = resourcesProvider.getDrawable(R.drawable.onion_3),
                type = Topping.ONION,
            ),
            ToppingEntity(
                imageRes = resourcesProvider.getDrawable(R.drawable.broccoli_3),
                type = Topping.BROCCOLI,
            ),
            ToppingEntity(
                imageRes = resourcesProvider.getDrawable(R.drawable.mushroom_3),
                type = Topping.MUSHROOM,
            ),
            ToppingEntity(
                imageRes = resourcesProvider.getDrawable(R.drawable.sausage_3),
                type = Topping.SAUSAGE,
            ),
        )

    val pizzas = listOf(
        PizzaEntity(breadImageRes = R.drawable.bread_1),
        PizzaEntity(breadImageRes = R.drawable.bread_2),
        PizzaEntity(breadImageRes = R.drawable.bread_3),
        PizzaEntity(breadImageRes = R.drawable.bread_4),
        PizzaEntity(breadImageRes = R.drawable.bread_5)
    )
}