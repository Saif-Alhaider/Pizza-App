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
                singleItemImageRes = resourcesProvider.getDrawable(R.drawable.basil_3),
                groupImageRes = resourcesProvider.getDrawable(R.drawable.group_basil),
                type = Topping.BASIL,
            ),
            ToppingEntity(
                singleItemImageRes = resourcesProvider.getDrawable(R.drawable.onion_3),
                groupImageRes = resourcesProvider.getDrawable(R.drawable.group_onion),
                type = Topping.ONION,
            ),
            ToppingEntity(
                singleItemImageRes = resourcesProvider.getDrawable(R.drawable.broccoli_3),
                groupImageRes = resourcesProvider.getDrawable(R.drawable.group_broccoli),
                type = Topping.BROCCOLI,
            ),
            ToppingEntity(
                singleItemImageRes = resourcesProvider.getDrawable(R.drawable.mushroom_3),
                groupImageRes = resourcesProvider.getDrawable(R.drawable.group_mushroom),
                type = Topping.MUSHROOM,
            ),
            ToppingEntity(
                singleItemImageRes = resourcesProvider.getDrawable(R.drawable.sausage_3),
                groupImageRes = resourcesProvider.getDrawable(R.drawable.group_sausage),
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