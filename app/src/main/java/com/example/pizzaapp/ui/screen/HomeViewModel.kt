package com.example.pizzaapp.ui.screen

import androidx.lifecycle.ViewModel
import com.example.pizzaapp.entity.PizzaSize
import com.example.pizzaapp.entity.Topping
import com.example.pizzaapp.usecase.GetPizzaToppingsUseCase
import com.example.pizzaapp.usecase.GetPizzasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPizzasUseCase: GetPizzasUseCase,
    private val getPizzaToppingsUseCase: GetPizzaToppingsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    init {
        getPizzas()
        getPizzaToppings()
    }

    fun updateCurrentPizza(currentPizzaIndex: Int) {
        _state.update { it.copy(currentPizzaIndex = currentPizzaIndex) }
    }

    private fun getPizzaToppings() {
        _state.update {
            it.copy(pizzas = it.pizzas.map { pizza ->
                pizza.copy(toppings = getPizzaToppingsUseCase().mapToppingToUi())
            })
        }
    }

    fun updateToppingState(type: Topping, isActive: Boolean) {
        _state.update {
            it.copy(
                pizzas = it.pizzas.mapIndexed { index, pizza ->
                    if (index == _state.value.currentPizzaIndex) {
                        pizza.copy(toppings = pizza.toppings.map { topping ->
                            if (topping.type == type) {
                                topping.copy(isActive = isActive)
                            } else {
                                topping
                            }
                        })
                    } else {
                        pizza
                    }
                }
            )
        }
    }

    private fun getPizzas() {
        _state.update { it.copy(pizzas = getPizzasUseCase().mapPizzaToUi()) }
    }

    fun updatePizzaSize(pizzaSize: PizzaSize) {
        _state.update {
            it.copy(pizzas = it.pizzas.mapIndexed { index, pizza ->
                if (index == _state.value.currentPizzaIndex) {
                    pizza.copy(size = pizzaSize)
                } else {
                    pizza
                }
            })
        }
    }
}