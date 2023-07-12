package com.example.pizzaapp.di

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResourcesProvider @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun getString(@StringRes value: Int): String {
        return context.getString(value)
    }

    fun getDrawable(@DrawableRes value: Int): Int {
        return value
    }
}