package com.example.pizzaapp.ui.screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pizzaapp.R
import com.example.pizzaapp.entity.PizzaSize
import com.example.pizzaapp.entity.Topping

@Composable
fun PizzaScreen() {
    val viewModel: HomeViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState().value
    PizzaContent(
        state = state,
        updateToppingState = viewModel::updateToppingState,
        updatePizzaSize = viewModel::updatePizzaSize,
        updateCurrentPizza = viewModel::updateCurrentPizza
    )
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun PizzaContent(
    state: HomeUiState,
    updateToppingState: (type: Topping, isActive: Boolean) -> Unit,
    updatePizzaSize: (PizzaSize) -> Unit,
    updateCurrentPizza: (Int) -> Unit
) {
    val sizeCircleAlignment by animateHorizontalAlignmentAsState(
        when (state.pizzas[state.currentPizzaIndex].size) {
            is PizzaSize.Small -> -1f
            is PizzaSize.Medium -> 0f
            is PizzaSize.Large -> 1f
        }
    )
    val pager = rememberPagerState()
    val size = animateFloatAsState(state.pizzas[state.currentPizzaIndex].size.scale)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        //region header
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "back icon",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = "Pizza",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Image(
                painter = painterResource(id = R.drawable.ic_heart),
                contentDescription = "heart icon",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                modifier = Modifier.size(24.dp)
            )
        }
        //endregion

        //region pizza pager
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxHeight(.5f)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.plate),
                contentDescription = "plate",
                modifier = Modifier.padding(32.dp)
            )
            HorizontalPager(
                state = pager,
                pageCount = state.pizzas.size,
                modifier = Modifier
                    .background(Color.Transparent)
                    .fillMaxWidth(),
                key = { state.pizzas[it].breadImageRes }
            ) { currentPage ->
                updateCurrentPizza(pager.currentPage)
                Box(
                    modifier = Modifier
                        .scale(size.value)
                ) {
                    Image(
                        painter = painterResource(id = state.pizzas[currentPage].breadImageRes),
                        contentDescription = "bread",
                    )
                    if (!pager.isScrollInProgress) {
                        state.pizzas[state.currentPizzaIndex].toppings.reversed().forEach {
                            androidx.compose.animation.AnimatedVisibility(
                                visible = it.isActive && currentPage == pager.currentPage,
                                enter = scaleIn(initialScale = 2f) + fadeIn(),
                                exit = fadeOut()
                            ) {
                                Image(
                                    painter = painterResource(id = it.groupImageRes),
                                    contentDescription = null,
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }
                    } else {
                        state.pizzas[currentPage].toppings.reversed().filter { it.isActive }
                            .forEach {
                                Image(
                                    painter = painterResource(id = it.groupImageRes),
                                    contentDescription = null,
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                    }
                }
            }

        }
        //endregion

        //region price
        Text(
            text = "17$",
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(top = 8.dp)
        )
        //endregion

        //region Pizza Size
        Box(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(.4f)
                .height(50.dp)
        ) {
            Surface(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(color = MaterialTheme.colorScheme.background)
                    .padding(4.dp)
                    .shadow(
                        elevation = 4.dp,
                        shape = CircleShape,
                        ambientColor = Color.Black.copy(alpha = .2f)
                    )
                    .align(sizeCircleAlignment)
            ) {

            }
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 6.dp)
            ) {
                Text(
                    text = "S",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) { updatePizzaSize(PizzaSize.Small) },
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "M",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            updatePizzaSize(PizzaSize.Medium)
                        },
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "L",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            updatePizzaSize(PizzaSize.Large)
                        },
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

        }
        //endregion

        //region pizza toppings
        Text(
            text = "CUSTOMIZ YOUR PIZZA",
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = .6f),
            modifier = Modifier
                .padding(top = 24.dp, start = 16.dp)
                .align(Alignment.Start)
        )

        LazyRow(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(40.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(state.pizzas[state.currentPizzaIndex].toppings) { topping ->
                Image(
                    painter = painterResource(id = topping.singleItemImageRes),
                    contentDescription = "topping",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(50.dp)
                        .background(color = if (topping.isActive) Color(0xFFdaf6e0) else Color.Transparent)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            updateToppingState(topping.type, !topping.isActive)
                        }
                )
            }
        }
        //endregion

        //region add to cart button
        Box(Modifier.weight(1f), contentAlignment = Alignment.BottomCenter) {
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(Color(0xFF443533)),
                shape = RoundedCornerShape(24),
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_cart),
                        contentDescription = "cart",
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Add to Cart",
                        fontSize = 24.sp,
                        color = Color.White,
                        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                    )
                }
            }
        }
        //endregion
    }

}

@Preview
@Composable
fun PizzaContentPreview() {
    fun updateToppingState(type: Topping, isActive: Boolean) {
    }

    PizzaContent(HomeUiState(
        pizzas = listOf(HomeUiState.PizzaUiState(breadImageRes = R.drawable.bread_1))
    ), ::updateToppingState, updatePizzaSize = { PizzaSize.Medium }, updateCurrentPizza = {})
}

@Composable
private fun animateHorizontalAlignmentAsState(
    targetBiasValue: Float
): State<BiasAlignment> {
    val bias by animateFloatAsState(targetBiasValue)
    return remember { derivedStateOf { BiasAlignment(horizontalBias = bias, verticalBias = 0f) } }
}